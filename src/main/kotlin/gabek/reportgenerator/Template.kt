package gabek.reportgenerator

import org.jtwig.JtwigTemplate
import java.io.File

class Template(filePath: String) {
    val jtwig: JtwigTemplate = JtwigTemplate.fileTemplate(File(filePath))
}