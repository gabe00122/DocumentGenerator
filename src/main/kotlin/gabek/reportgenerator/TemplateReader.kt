package gabek.reportgenerator

import gabek.reportgenerator.worddom.Document
import java.io.ByteArrayInputStream
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory


fun buildModel(bytes: ByteArray): Document{
    val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()

    val doc = Document()
    doc.loadXML(documentBuilder.parse(ByteArrayInputStream(bytes)).documentElement)

    return doc
}

