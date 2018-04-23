package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.w3c.dom.Node

class Document(document: Node): WordNode<XWPFDocument>(document.nodeName) {
    private val nodeList = ArrayList<WordNode<XWPFDocument>>()

    init {
        loadXML(document)
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)

        baseNode.normalize()
        var childNode = baseNode.firstChild

        while (childNode != null) {
            when (childNode.nodeName) {
                "p" -> nodeList.add(Paragraph(childNode))
                "table" -> nodeList.add(Table(childNode))
            }
            childNode = childNode.nextSibling
        }
    }

    fun render(styleMap: Map<String, Style>)
            = XWPFDocument().also { generateTo(it, null, styleMap) }


    override fun generateTo(model: XWPFDocument, parentStyle: StyleNode?, styleMap: Map<String, Style>) {
        super.generateTo(model, parentStyle, styleMap)

        val nodes = styleNodes(parentStyle, styleMap)
        nodeList.forEach { it.generateTo(model, nodes, styleMap) }
    }
}
