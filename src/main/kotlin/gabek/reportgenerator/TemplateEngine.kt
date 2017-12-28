package gabek.reportgenerator

import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import java.io.File

fun getTemplateEngine(): Configuration {
    val config = Configuration(Configuration.VERSION_2_3_23)

    config.setDirectoryForTemplateLoading(File("data"))
    config.defaultEncoding = "UTF-8"
    config.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
    config.logTemplateExceptions = false

    return config
}