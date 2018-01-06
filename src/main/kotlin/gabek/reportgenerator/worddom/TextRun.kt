package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun
import org.w3c.dom.Node

class TextRun(baseNode: Node, styleNode: StyleNode) : WordNode<XWPFParagraph>(baseNode.nodeName, styleNode) {
    private var text = ""

    init {
        loadXML(baseNode)
    }

    override fun generateTo(model: XWPFParagraph, styleMap: Map<String, Style>){
        super.generateTo(model, styleMap)

        val dRun = model.createRun()
        applyStyle(dRun, styleMap, style)
        dRun.setText(text)
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)

        text = if (baseNode.nodeType == Node.ELEMENT_NODE) {
            baseNode.firstChild.nodeValue
        } else {
            baseNode.nodeValue
        }
        text = text.replace(PATTERN, "\n")
    }

    private tailrec fun applyStyle(r: XWPFRun, context: Map<String, Style>, styleNode: StyleNode) {
        context[styleNode.style]?.applyToRun(r)

        if (styleNode.parentNode != null) {
            applyStyle(r, context, styleNode.parentNode)
        }
    }

    companion object {
        private val PATTERN = "\n\\s+".toRegex()
    }
}
