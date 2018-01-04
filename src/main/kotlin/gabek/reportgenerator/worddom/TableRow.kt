package gabek.reportgenerator.worddom

import gabek.reportgenerator.StyleNode
import org.apache.poi.xwpf.usermodel.XWPFTable
import org.apache.poi.xwpf.usermodel.XWPFTableRow
import org.w3c.dom.Node

class TableRow(baseNode: Node, styleNode: StyleNode, private val number: Int) : WordNode<XWPFTable> {
    private val nodeList = ArrayList<WordNode<XWPFTableRow>>()
    override val style = StyleNode(baseNode.nodeName, styleNode)

    init {
        loadXML(baseNode)
    }

    override fun generateTo(model: XWPFTable, styleMap: Map<String, Style>) {
        val row = model.getRow(number) ?: model.createRow()

        for (node in nodeList) {
            node.generateTo(row, styleMap)
        }
    }

    override fun loadXML(baseNode: Node) {
        var childNode = baseNode.firstChild

        var index = 0
        while (childNode != null) {
            if (childNode.nodeName == "tc") {
                nodeList.add(TableCell(childNode, style, index++))
            }

            childNode = childNode.nextSibling
        }
    }
}