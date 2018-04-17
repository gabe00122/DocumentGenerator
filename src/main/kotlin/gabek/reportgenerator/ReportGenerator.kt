package gabek.reportgenerator

import gabek.reportgenerator.input.ExelLoader
import gabek.reportgenerator.input.InputPatternLoader
import gabek.reportgenerator.style.loadStyles
import java.io.File
import java.io.FileOutputStream

fun main(args: Array<String>) {
    val inputScheme = InputPatternLoader.loadInputPattern(File("data/input_pattern.xml"))

    val data = ExelLoader.loadData(inputScheme, "data/input.xlsx")
    val styles = loadStyles("data/styles.xml")

    val model = buildModel("report_template.ftl", data[2])
    val xDoc = model.generate(styles)

    FileOutputStream("data/output.docx").use { outputStream ->
        xDoc.write(outputStream)
    }
}
