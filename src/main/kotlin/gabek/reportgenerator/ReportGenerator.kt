package gabek.reportgenerator

import gabek.reportgenerator.input.CvsLoader
import gabek.reportgenerator.style.loadStyles
import java.io.FileOutputStream

fun main(args: Array<String>) {
    //val inputScheme = InputPatternLoader.loadInputPattern(File("data/input_pattern.xml"))

    val data = CvsLoader.loadData("data/data.csv")
    val styles = loadStyles("data/styles.xml")
    val template = Template("data/simple_temp.xml")

    for (i in 0 until data.size) {
        val person = data[i]
        val fileName = "${person["FirstName"]}_${person["LastName"]}"

        println("Generation file for: $fileName")

        val model = buildModel(template, person)
        val xDoc = model.render(styles)

        FileOutputStream("data/output/$fileName.docx").use { outputStream ->
            xDoc.write(outputStream)
        }
    }
}
