package gabek.reportgenerator.worddom

import gabek.reportgenerator.StyleNode
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFTable
import org.w3c.dom.Node

class Table(baseNode: Node, styleNode: StyleNode) : WordNode<XWPFDocument> {
    private val nodeList = ArrayList<WordNode<XWPFTable>>()
    override val style = StyleNode(baseNode.nodeName, styleNode)

    init {
        loadXML(baseNode)
    }

    override fun loadXML(baseNode: Node) {
        var childNode = baseNode.firstChild

        var index = 0
        while (childNode != null) {
            if (childNode.nodeName == "tr") {
                nodeList.add(TableRow(childNode, style, index++))
            }

            childNode = childNode.nextSibling
        }
    }

    override fun generateTo(model: XWPFDocument, styleMap: Map<String, Style>) {
        val table = model.createTable()

        for (node in nodeList) {
            node.generateTo(table, styleMap)
        }
    }
}