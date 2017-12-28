package gabek.reportgenerator

class StyleNode(val style: String, val parentNode: StyleNode? = null) {
    companion object {
        fun styles(vararg styles: String, parentNode: StyleNode? = null): StyleNode {
            if (styles.isEmpty()) {
                throw IllegalArgumentException()
            }

            return Companion.styles(styles.size-1, styles, parentNode)!!
        }

        private fun styles(i: Int, styles: Array<out String>, parentNode: StyleNode?): StyleNode? {
            return if (i >= 0) {
                StyleNode(styles[i], styles(i-1, styles, parentNode) ?: parentNode)
            } else {
                null
            }
        }
    }
}