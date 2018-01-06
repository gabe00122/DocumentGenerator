package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFTableRow
import org.w3c.dom.Node

class TableCell(baseNode: Node, styleNode: StyleNode, private val number: Int) : WordNode<XWPFTableRow>(baseNode.nodeName, styleNode) {
    var text = ""

    init {
        loadXML(baseNode)
    }

    override fun generateTo(model: XWPFTableRow, styleMap: Map<String, Style>) {
        super.generateTo(model, styleMap)
        val cell = model.getCell(number) ?: model.createCell()
        cell.text = text
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)
        text = baseNode.textContent
    }
}
