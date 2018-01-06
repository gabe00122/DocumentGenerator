package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style

class StyleNode(val style: String, val parentNode: StyleNode? = null) {
    companion object {
        fun styles(styles: Array<out String>, parentNode: StyleNode? = null): StyleNode {
            if (styles.isEmpty()) {
                throw IllegalArgumentException()
            }

            return styles(styles.size - 1, styles, parentNode)!!
        }

        private fun styles(i: Int, styles: Array<out String>, parentNode: StyleNode?): StyleNode? {
            return if (i >= 0) {
                StyleNode(styles[i], styles(i - 1, styles, parentNode) ?: parentNode)
            } else {
                null
            }
        }
    }

    inline fun applyStyles(styleMap: Map<String, Style>, block: Style.() -> Unit) {
        styleMap[style]?.block()

        var parent = parentNode
        while (parent != null){
            styleMap[parent.style]?.block()
            parent = parent.parentNode
        }
    }
}
