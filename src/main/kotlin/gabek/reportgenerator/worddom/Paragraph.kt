package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.w3c.dom.Node

class Paragraph(baseNode: Node, styleNode: StyleNode) : WordNode<XWPFDocument>(baseNode.nodeName, styleNode) {
    private val nodeList = ArrayList<WordNode<XWPFParagraph>>()

    init {
        loadXML(baseNode)
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)
        var childNode = baseNode.firstChild

        while (childNode != null) {
            nodeList.add(TextRun(childNode, style))
            childNode = childNode.nextSibling
        }
    }

    override fun generateTo(model: XWPFDocument, styleMap: Map<String, Style>) {
        super.generateTo(model, styleMap)
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
