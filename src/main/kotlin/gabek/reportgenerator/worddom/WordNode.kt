package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.w3c.dom.Node

abstract class WordNode<in G>(primaryStyle: String?, parentStyle: StyleNode) {
    var style = if (primaryStyle == null) parentStyle else StyleNode(primaryStyle, parentStyle)
        private set

    open fun loadXML(baseNode: Node) {
        val styleNode = baseNode.attributes?.getNamedItem("style")

        if (styleNode != null) {
            val ss = styleNode.nodeValue.split("\\s*,\\s*".toRegex())
            style = StyleNode.styles(ss.toTypedArray(), style)
        }
    }

    open fun generateTo(model: G, styleMap: Map<String, Style>) {}
}
