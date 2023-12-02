package adventofcode.dayone

import kotlin.test.Test
import kotlin.test.assertEquals

class TrebuchetCalibrationTest {

    val calibrationTest: TrebuchetCalibration = TrebuchetCalibration()

    @Test fun oneDigit() {
        val testInput: List<String> = listOf("test3test")
        val result: Int = calibrationTest.process(testInput)

        assertEquals(33, result)
    }

    @Test fun twoDigit() {
        val testInput: List<String> = listOf("te1st3test")
        val result: Int = calibrationTest.process(testInput)

        assertEquals(13, result)
    }

    @Test fun threeDigit() {
        val testInput: List<String> = listOf("te1st3te5st")
        val result: Int = calibrationTest.process(testInput)

        assertEquals(15, result)
    }

    @Test fun fourDigit() {
        val testInput: List<String> = listOf("te23st35test")
        val result: Int = calibrationTest.process(testInput)

        assertEquals(25, result)
    }

    @Test fun sumMultiple() {
        val testInput: List<String> = listOf("te23st35test", "nts2stiae3th4", "tnsai42nei12i4")
        val result: Int = calibrationTest.process(testInput)

        assertEquals((25+24+44), result)
    }

    @Test fun handlesStringDigits() {
        val testInput: List<String> = listOf("five39six")
        val result: Int = calibrationTest.process(testInput)

        assertEquals(56, result)
    }

    @Test fun handlesConjoinedDigits() {
        val testInput: List<String> = listOf("twone")
        val result: Int = calibrationTest.process(testInput)

        assertEquals(21, result)
    }
}