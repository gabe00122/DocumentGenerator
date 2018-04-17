package gabek.reportgenerator.input

import gabek.reportgenerator.parseXML
import java.io.File
import java.lang.Math.pow

object InputPatternLoader {
    fun loadInputPattern(file: File): List<String?> {
        val doc = parseXML(file)
        val outputPattern = ArrayList<String?>()

        var node = doc.firstChild
        while (node != null) {

            val name = node.attributes?.getNamedItem("name")
            val col = node.attributes?.getNamedItem("col")

            if (name != null && col != null) {
                val index = columnToIndex(col.nodeValue)

                while (index >= outputPattern.size) {
                    outputPattern.add(null)
                }
                outputPattern[index] = name.nodeValue
            }

            node = node.nextSibling
        }

        return outputPattern
    }

    private fun columnToIndex(columnCode: String): Int =
            (0 until columnCode.length).sumBy {
                (columnCode[it] - '@') * pow(26.0, (columnCode.length - it - 1).toDouble()).toInt()
            } - 1
}