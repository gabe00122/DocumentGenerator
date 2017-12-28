package gabek.reportgenerator.worddom

import gabek.reportgenerator.StyleNode
import org.w3c.dom.Node

interface WordNode<in G> {
    val style: StyleNode

    fun loadXML(baseNode: Node)
    fun generateTo(model: G, styleMap: Map<String, Style>)
}
