package gabek.reportgenerator.worddom

import gabek.reportgenerator.StyleNode
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.w3c.dom.Node

class Paragraph(baseNode: Node, styleNode: StyleNode) : WordNode<XWPFDocument> {
    private val nodeList = ArrayList<WordNode<XWPFParagraph>>()
    override val style: StyleNode = StyleNode(baseNode.nodeName, styleNode)

    init {
        loadXML(baseNode)
    }

    override fun loadXML(baseNode: Node) {
        var childNode = baseNode.firstChild

        while (childNode != null) {
            nodeList.add(TextRun(childNode, style))
            childNode = childNode.nextSibling
        }
    }

    override fun generateTo(model: XWPFDocument, styleMap: Map<String, Style>) {
        val p = model.createParagraph()
        applyStyle(p, styleMap, style)
        for (r in nodeList) {
            r.generateTo(p, styleMap)
        }
    }

    private tailrec fun applyStyle(p: XWPFParagraph, context: Map<String, Style>, styleNode: StyleNode){
        context[styleNode.style]?.applyToParagraph(p)

        if (styleNode.parentNode != null) {
            applyStyle(p, context, styleNode.parentNode)
        }
    }
}
