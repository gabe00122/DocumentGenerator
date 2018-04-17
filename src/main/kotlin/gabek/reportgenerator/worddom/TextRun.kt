package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun
import org.w3c.dom.Node

class TextRun(baseNode: Node) : WordNode<XWPFParagraph>(baseNode.nodeName) {
    private var text = ""

    init {
        loadXML(baseNode)
    }

    override fun generateTo(model: XWPFParagraph, parentStyle: StyleNode?, styleMap: Map<String, Style>) {
        super.generateTo(model, parentStyle, styleMap)

        if (text.isNotBlank()) {
            val r = model.createRun()
            styleNodes(parentStyle, styleMap)?.applyStyle(r)

            val lines = text.split("\n")
            for (i in 0 until lines.size) {
                r.setText(lines[i])
                if (i < lines.size - 1 || lines[i].isBlank()) {
                    r.addCarriageReturn()
                }
            }
        }
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)

        text = if (baseNode.nodeType == Node.ELEMENT_NODE) {
            baseNode.firstChild?.nodeValue
        } else {
            baseNode.nodeValue
        } ?: ""
        text = text.replace(PATTERN, "\n")
    }

    companion object {
        private val PATTERN = "\\n\\s+".toRegex()
    }
}
