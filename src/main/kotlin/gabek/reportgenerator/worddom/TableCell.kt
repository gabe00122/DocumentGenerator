package gabek.reportgenerator.worddom

import gabek.reportgenerator.StyleNode
import org.apache.poi.xwpf.usermodel.XWPFTableRow
import org.w3c.dom.Node

class TableCell(baseNode: Node, styleNode: StyleNode, private val number: Int) : WordNode<XWPFTableRow> {
    override val style = StyleNode(baseNode.nodeName, styleNode)
    var text = ""

    init {
        loadXML(baseNode)
    }

    override fun generateTo(model: XWPFTableRow, styleMap: Map<String, Style>) {
        val cell = model.getCell(number) ?: model.createCell()
        //cell.text = text
        //val p = cell.addParagraph()
        //val r = p.createRun()
        cell.text = text
    }

    override fun loadXML(baseNode: Node) {
        text = baseNode.textContent
    }
}
