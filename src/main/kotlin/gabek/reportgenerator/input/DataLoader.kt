package gabek.reportgenerator.input

interface DataLoader {
    fun loadData(inputScheme: List<String?>, filePath: String): List<Map<String, String>>
}