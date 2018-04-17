package gabek.reportgenerator

import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

fun parseXML(file: File): Element {
    val docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    val doc = docBuilder.parse(file).documentElement
    doc.normalize()
    return doc
}