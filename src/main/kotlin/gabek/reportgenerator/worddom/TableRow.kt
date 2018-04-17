package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFTable
import org.apache.poi.xwpf.usermodel.XWPFTableRow
import org.w3c.dom.Node

class TableRow(baseNode: Node, private val number: Int) : WordNode<XWPFTable>(baseNode.nodeName) {
    private val nodeList = ArrayList<WordNode<XWPFTableRow>>()

    init {
        loadXML(baseNode)
    }

    override fun generateTo(model: XWPFTable, parentStyle: StyleNode?, styleMap: Map<String, Style>) {
        super.generateTo(model, parentStyle, styleMap)
        val row = model.getRow(number) ?: model.createRow()

        val styleNode = styleNodes(parentStyle, styleMap)
        for (node in nodeList) {
            node.generateTo(row, styleNode, styleMap)
        }
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)
        var childNode = baseNode.firstChild

        var index = 0
        while (childNode != null) {
            if (childNode.nodeName == "tc") {
                nodeList.add(TableCell(childNode, index++))
            }

            childNode = childNode.nextSibling
        }
    }
}