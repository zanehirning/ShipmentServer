import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CsvParserTest {
    @Test
    fun testCsvParserConstruction() {
        val csvParser = CsvParser("src/main/kotlin/input/test.txt")
        assertTrue(csvParser is CsvParser)
    }

    @Test
    fun testParse() {
        val csvParser = CsvParser("src/main/kotlin/input/test.txt")
        val line = csvParser.parseLine()
        assertTrue(line.isNotEmpty())
        assertTrue(line.size == 3)
        assertEquals("created", line[0])
        assertEquals("s10000", line[1])
        assertEquals("1652712855468", line[2])

        val line2 = csvParser.parseLine()
        assertTrue(line2.isNotEmpty())
        assertTrue(line2.size == 3)
        assertEquals("created", line2[0])
        assertEquals("s10001", line2[1])
        assertEquals("1652712855468", line2[2])
    }

    @Test
    fun testHasNextLine() {
        val csvParser = CsvParser("src/main/kotlin/input/test.txt")
        assertTrue(csvParser.hasNextLine())
    }
}