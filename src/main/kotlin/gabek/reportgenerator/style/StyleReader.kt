package gabek.reportgenerator.style

import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Node

fun loadStyles(stylePath: String): Map<String, Style> {
    val out = HashMap<String, Style>()

    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(File(stylePath)).documentElement
    doc.normalize()

    var child = doc.firstChild
    while (child != null) {
        if (child.nodeType == Node.ELEMENT_NODE) {
            val name = child.attributes.getNamedItem("name")

            if (name != null) {
                val style = Style()
                style.loadXML(child)
                out.put(name.nodeValue, style)
            }
        }

        child = child.nextSibling
    }

    return out
}