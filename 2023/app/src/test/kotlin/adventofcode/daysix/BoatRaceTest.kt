package adventofcode.daysix

import kotlin.test.Test
import kotlin.test.assertEquals

class BoatRaceTest {

    @Test fun testInput() {
        val testInput = listOf(
        "Time:      7  15   30",
        "Distance:  9  40  200",
        )

        assertEquals(288, BoatRace(testInput).calculateMargin())
    }

    @Test fun testInputDay2() {
        val testInput = listOf(
            "Time:      7  15   30",
            "Distance:  9  40  200",
        )

        assertEquals(71503, BoatRace(testInput).calculateSingleRace())
    }
}