package gabek.reportgenerator

import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import gabek.reportgenerator.worddom.Document
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.OutputStreamWriter
import javax.xml.parsers.DocumentBuilderFactory

fun getTemplateEngine(): Configuration {
    val config = Configuration(Configuration.VERSION_2_3_23)

    config.setDirectoryForTemplateLoading(File("data"))
    config.defaultEncoding = "UTF-8"
    config.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
    config.logTemplateExceptions = false

    return config
}

fun buildModel(template: String, data: Map<String, String>): Document {
    val engine = getTemplateEngine()

    val outputBuffer = ByteArrayOutputStream()
    engine.getTemplate(template).process(data, OutputStreamWriter(outputBuffer))

    val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    return Document(documentBuilder.parse(ByteArrayInputStream(outputBuffer.toByteArray())).documentElement)
}

