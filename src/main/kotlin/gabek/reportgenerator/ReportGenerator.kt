package gabek.reportgenerator

import gabek.reportgenerator.worddom.style
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val config = getTemplateEngine()

    val data = loadExelFile(inputScheme, "data/workbook.xlsx")
    val template = config.getTemplate("report_template.xml")
    val docBuffer = ByteArrayOutputStream()

    template.process(data.first(), OutputStreamWriter(docBuffer))

    val model = buildModel(docBuffer.toByteArray())
    val xDoc = model.generate(styles)

    FileOutputStream("data/test.docx").use { outputStream ->
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
        "FirstName", "LastName",
        null, null, null, null, null, null, null, null,
        "FallHours", null, "FallCourses",
        null, null, null, null, null,
        "SpringHours", null, "SpringCourses"
)




