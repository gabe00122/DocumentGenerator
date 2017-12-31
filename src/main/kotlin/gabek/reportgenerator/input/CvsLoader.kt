package gabek.reportgenerator.input

import java.io.FileReader

object CvsLoader: DataLoader {
    private val comaPattern = Regex("\\s*,\\s*")

    override fun loadData(inputScheme: List<String?>, filePath: String): List<Map<String, String>> {
        val out = ArrayList<HashMap<String, String>>()

        FileReader(filePath).use { reader ->
            val lines = reader.readLines()

            for (line in lines) {
                val values = line.split(comaPattern)
                val lineOut = HashMap<String, String>()
                val valueIter = values.iterator()
                val schemeIter = inputScheme.iterator()

                while (schemeIter.hasNext()) {
                    val value = valueIter.next()
                    val slot = schemeIter.next()

                    if (slot != null) {
                        lineOut.put(slot, value)
                    }
                }
                out.add(lineOut)
            }
        }

        return out
    }
}