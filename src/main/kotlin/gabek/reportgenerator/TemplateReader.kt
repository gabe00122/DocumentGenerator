package gabek.reportgenerator

import gabek.reportgenerator.worddom.Document
import org.jtwig.JtwigModel
import org.jtwig.JtwigTemplate
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.OutputStreamWriter
import javax.xml.parsers.DocumentBuilderFactory


fun buildModel(template: Template, data: Map<String, String>): Document {
    val outputBuffer = ByteArrayOutputStream()
    template.jtwig.render(JtwigModel.newModel(data), outputBuffer)

    val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    return Document(documentBuilder.parse(ByteArrayInputStream(outputBuffer.toByteArray())).documentElement)
}

