package adventofcode.daynine

import kotlin.test.Test
import kotlin.test.assertEquals

class MirageTest {

    @Test fun testInput() {
        assertEquals(114, Mirage(arrayListOf(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45",
        )).extrapolatesSum())
    }

    @Test fun testInput2() {
        assertEquals(2, Mirage(arrayListOf(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45",
        )).extrapolatesBeginningSum())
    }
}