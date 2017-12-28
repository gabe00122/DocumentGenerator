package gabek.reportgenerator.worddom

import gabek.reportgenerator.StyleNode
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun
import org.w3c.dom.Node

class TextRun(baseNode: Node, styleNode: StyleNode): AbstractRun() {
    override val style: StyleNode = if (baseNode.nodeType == Node.ELEMENT_NODE) {
        StyleNode(baseNode.nodeName, styleNode)
    } else {
        styleNode
    }

    init {
        loadXML(baseNode)
    }

    override fun generateTo(model: XWPFParagraph, styleMap: Map<String, Style>){
        val dRun = model.createRun()
        applyStyle(dRun, styleMap, style)
        dRun.setText(text)
    }

    private tailrec fun applyStyle(r: XWPFRun, context: Map<String, Style>, styleNode: StyleNode) {
        context[styleNode.style]?.applyToRun(r)

        if (styleNode.parentNode != null) {
            applyStyle(r, context, styleNode.parentNode)
        }
    }

    override fun loadXML(baseNode: Node) {
        text = if (baseNode.nodeType == Node.ELEMENT_NODE) {
            baseNode.firstChild.nodeValue
        } else {
            baseNode.nodeValue
        }
        text = text.replace("\n\\s+".toRegex(), "\n")
    }
}
