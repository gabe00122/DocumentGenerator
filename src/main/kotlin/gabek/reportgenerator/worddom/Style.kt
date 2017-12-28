package gabek.reportgenerator.worddom

import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun

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
}

fun style(block: Style.() -> Unit): Style {
    val s = Style()
    s.block()
    return s
}