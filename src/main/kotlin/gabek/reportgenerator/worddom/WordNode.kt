package gabek.reportgenerator.worddom

import gabek.reportgenerator.style.Style
import org.w3c.dom.Node

abstract class WordNode<in G>(primaryStyle: String?) {
    private val _styles = ArrayList<String>()
    val styles: List<String> = _styles

    init {
        primaryStyle?.let { _styles.add(it) }
    }

    open fun loadXML(baseNode: Node) {
        val styleNode = baseNode.attributes?.getNamedItem("style")

        if (styleNode != null) {
            _styles.addAll(styleNode.nodeValue.split("\\s*,\\s*".toRegex()))
        }
    }

    open fun generateTo(model: G, parentStyle: StyleNode?, styleMap: Map<String, Style>) {}

    fun styleNodes(parentStyle: StyleNode?, context: Map<String, Style>)
            = StyleNode.styles(styles.mapNotNull { context[it] }, parentStyle)
}
