package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.w3c.dom.Node

class Paragraph(baseNode: Node) : WordNode<XWPFDocument>(baseNode.nodeName) {
    private val nodeList = ArrayList<WordNode<XWPFParagraph>>()

    init {
        loadXML(baseNode)
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)
        var childNode = baseNode.firstChild

        while (childNode != null) {
            nodeList.add(TextRun(childNode))
            childNode = childNode.nextSibling
        }
    }

    override fun generateTo(model: XWPFDocument, parentStyle: StyleNode?, styleMap: Map<String, Style>) {
        super.generateTo(model, parentStyle, styleMap)
        val p = model.createParagraph()

        val styleNode = styleNodes(parentStyle, styleMap)
        styleNode?.applyStyle(p)
        for (r in nodeList) {
            r.generateTo(p, styleNode, styleMap)
        }
    }
}
