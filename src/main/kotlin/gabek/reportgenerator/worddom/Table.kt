package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFTable
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth
import org.w3c.dom.Node
import java.math.BigInteger

class Table(baseNode: Node) : WordNode<XWPFDocument>(baseNode.nodeName) {
    private val nodeList = ArrayList<WordNode<XWPFTable>>()

    init {
        loadXML(baseNode)
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)
        var childNode = baseNode.firstChild

        var index = 0
        while (childNode != null) {
            if (childNode.nodeName == "tr") {
                nodeList.add(TableRow(childNode, index++))
            }

            childNode = childNode.nextSibling
        }
    }

    override fun generateTo(model: XWPFDocument, parentStyle: StyleNode?, styleMap: Map<String, Style>) {
        super.generateTo(model, parentStyle, styleMap)
        val table = model.createTable()

        val width = table.ctTbl.addNewTblPr().addNewTblW()
        width.type = STTblWidth.DXA
        width.w = BigInteger.valueOf(9072)

        val styleNode = styleNodes(parentStyle, styleMap)
        for (node in nodeList) {
            node.generateTo(table, styleNode, styleMap)
        }
    }
}