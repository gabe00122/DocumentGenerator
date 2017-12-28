package gabek.reportgenerator

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream


fun loadExelFile(inputScheme: List<String?>, filePath: String): List<Map<String, String>> {
    val dataList = ArrayList<Map<String, String>>()

    FileInputStream(filePath).use { inputStream ->
        val workbook = XSSFWorkbook(inputStream)
        val sheet = workbook.getSheetAt(0)

        val rowIterator = sheet.iterator()
        rowIterator.next()

        while (rowIterator.hasNext()) {
            val row = rowIterator.next()
            val cellIterator = row.cellIterator()
            val data = LinkedHashMap<String, String>()

            while (cellIterator.hasNext()) {
                val cell = cellIterator.next()
                val slot = inputScheme.getOrNull(cell.columnIndex)
                cell.columnIndex
                if (slot != null) {
                    val value: String = when (cell.cellTypeEnum) {
                        CellType.STRING -> cell.stringCellValue
                        CellType.NUMERIC -> cell.numericCellValue.toString()
                        CellType.BOOLEAN -> cell.booleanCellValue.toString()
                        else -> ""
                    }

                    data[slot] = value
                }
            }
            dataList.add(data)
        }
    }

    return dataList
}
