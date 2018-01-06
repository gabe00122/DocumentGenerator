package gabek.reportgenerator

import gabek.reportgenerator.input.CvsLoader
import gabek.reportgenerator.style.loadStyles
import java.io.FileOutputStream

fun main(args: Array<String>) {
    val data = CvsLoader.loadData(inputScheme, "data/test_input.csv")
    val styles = loadStyles("data/styles.xml")

    val model = buildModel("template.xml", data.first())
    val xDoc = model.generate(styles)

    FileOutputStream("data/output.docx").use { outputStream ->
        xDoc.write(outputStream)
    }

}

val inputScheme = listOf(
        "FirstName", "LastName", "Role"
)
