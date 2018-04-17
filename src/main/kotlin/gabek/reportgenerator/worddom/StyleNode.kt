package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun

class StyleNode(val style: Style, val parentNode: StyleNode? = null) {

    fun applyStyle(p: XWPFParagraph) {
        style.applyToParagraph(p)
        parentNode?.applyStyle(p)
    }

    fun applyStyle(r: XWPFRun) {
        style.applyToRun(r)
        parentNode?.applyStyle(r)
    }

    companion object {
        fun styles(styles: List<Style>, parentNode: StyleNode? = null): StyleNode? {
            if (styles.isEmpty()) {
                return parentNode
            }

            return styles(styles.size - 1, styles, parentNode)!!
        }

        private fun styles(i: Int, styles: List<Style>, parentNode: StyleNode?): StyleNode? {
            return if (i >= 0) {
                StyleNode(styles[i], styles(i - 1, styles, parentNode) ?: parentNode)
            } else {
                null
            }
        }
    }
}
