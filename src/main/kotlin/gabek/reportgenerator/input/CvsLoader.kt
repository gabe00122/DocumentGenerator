package gabek.reportgenerator.input

import java.io.FileReader

object CvsLoader {
    private val comaPattern = Regex("\\s*,\\s*")

    fun loadData(filePath: String): List<Map<String, String>> {
        val out = ArrayList<HashMap<String, String>>()

        FileReader(filePath).use { reader ->
            val lines = reader.readLines()
            val labels = lines.first().split(comaPattern)

            for (i in 1 until lines.size) {
                val line = lines[i]
                val values = line.split(comaPattern)
                val lineOut = HashMap<String, String>()
                val valueIter = values.iterator()
                val schemeIter = labels.iterator()

                while (schemeIter.hasNext()) {
                    val value = valueIter.next()
                    val slot = schemeIter.next()

                    lineOut[slot] = value
                }
                out.add(lineOut)
            }
        }

        return out
    }
}