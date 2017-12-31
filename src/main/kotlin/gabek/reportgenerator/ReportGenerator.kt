package gabek.reportgenerator

import gabek.reportgenerator.input.CvsLoader
import gabek.reportgenerator.worddom.style
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val config = getTemplateEngine()

    val data = CvsLoader.loadData(inputScheme, "data/test_input.csv")
    val template = config.getTemplate("template.xml")
    val docBuffer = ByteArrayOutputStream()

    template.process(data.first(), OutputStreamWriter(docBuffer))

    val model = buildModel(docBuffer.toByteArray())
    val xDoc = model.generate(styles)

    FileOutputStream("data/output.docx").use { outputStream ->
        xDoc.write(outputStream)
    }
}

val styles = mapOf(
        "document" to style {
            fontFamily = "Times New Roman"
            fontSize = 12
        },
        "title" to style {
            paragraphAlignment = ParagraphAlignment.CENTER
        },
        "v" to style {
            fontColor = "ff0000"
        },
        "b" to style {
            isBold = true
        }
)

val inputScheme = listOf(
        "FirstName", "LastName", "Role"
)
