package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFTableRow
import org.w3c.dom.Node

class TableCell(baseNode: Node, private val number: Int) : WordNode<XWPFTableRow>(baseNode.nodeName) {
    var text = ""

    init {
        loadXML(baseNode)
    }

    override fun generateTo(model: XWPFTableRow, parentStyle: StyleNode?, styleMap: Map<String, Style>) {
        super.generateTo(model, parentStyle, styleMap)

        val styleNode = styleNodes(parentStyle, styleMap)

        val cell = model.getCell(number) ?: model.createCell()
        val p = cell.paragraphs.first()
        val r = p.createRun()

        r.setText(text)

        styleNode?.applyStyle(p)
        styleNode?.applyStyle(r)
    }

    override fun loadXML(baseNode: Node) {
        super.loadXML(baseNode)
        text = baseNode.textContent
    }
}
