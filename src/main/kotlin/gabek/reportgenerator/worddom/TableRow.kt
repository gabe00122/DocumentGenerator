package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFTable
import org.apache.poi.xwpf.usermodel.XWPFTableRow
import org.w3c.dom.Node

class TableRow(baseNode: Node, styleNode: StyleNode, private val number: Int) : WordNode<XWPFTable>(baseNode.nodeName, styleNode) {
    private val nodeList = ArrayList<WordNode<XWPFTableRow>>()

    init {
        loadXML(baseNode)
    }

    override fun generateTo(model: XWPFTable, styleMap: Map<String, Style>) {
        super.generateTo(model, styleMap)
        val row = model.getRow(number) ?: model.createRow()

        for (node in nodeList) {
            node.generateTo(row, styleMap)
        }
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)
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