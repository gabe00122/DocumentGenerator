package gabek.reportgenerator.style

import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun
import org.w3c.dom.Node

class Style {
    var fontFamily: String? = null
    var fontSize: Int? = null
    var fontColor: String? = null
    var isBold: Boolean? = null

    var paragraphAlignment: ParagraphAlignment? = null

    internal fun applyToRun(r: XWPFRun) {
        fontFamily?.let { r.fontFamily = it }
        fontSize?.let { r.fontSize = it }
        fontColor?.let { r.color = it }
        isBold?.let { r.isBold = it }
    }

    internal fun applyToParagraph(p: XWPFParagraph) {
        paragraphAlignment?.let { p.alignment = it }
    }

    fun loadXML(node: Node) = with (node.attributes) {
        getNamedItem("fontFamily")?.let { fontFamily = it.nodeValue }
        getNamedItem("fontSize")?.let { fontSize = it.nodeValue.toInt() }
        getNamedItem("fontColor")?.let { fontColor = it.nodeValue }
        getNamedItem("isBold")?.let { isBold = it.nodeValue.toBoolean() }

        getNamedItem("paragraphAlignment")?.let { paragraphAlignment = ParagraphAlignment.valueOf(it.nodeValue) }
    }
}

fun style(block: Style.() -> Unit): Style {
    val s = Style()
    s.block()
    return s
}