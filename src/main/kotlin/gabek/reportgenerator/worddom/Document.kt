package gabek.reportgenerator.worddom

import gabek.reportgenerator.StyleNode
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.w3c.dom.Node

class Document {
    private val nodeList = ArrayList<WordNode<XWPFDocument>>()
    private val style = StyleNode("document")

    fun loadXML(document: Node) {
        document.normalize()
        var childNode = document.firstChild

        while (childNode != null) {
            if (childNode.nodeType == Node.ELEMENT_NODE) {
                nodeList.add(Paragraph(childNode, style))
            }
            childNode = childNode.nextSibling
        }
    }

    fun generate(styleMap: Map<String, Style>): XWPFDocument {
        val xDoc = XWPFDocument()

        nodeList.forEach { it.generateTo(xDoc, styleMap) }

        return xDoc
    }
}
