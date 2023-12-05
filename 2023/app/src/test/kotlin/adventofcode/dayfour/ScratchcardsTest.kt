package adventofcode.dayfour

import kotlin.test.Test
import kotlin.test.assertEquals
class ScratchcardsTest {

    @Test fun oneCardWith2Matches () {
        assertEquals(2, Scratchcards(listOf("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19")).sumPoints())
    }

    @Test fun oneCard5Matches () {
        assertEquals(16, Scratchcards(listOf("Card 2: 13 32 20 16 61 | 13 32 20 16 61 87 66 33 45")).sumPoints())
    }

    @Test fun oneCard0Matches () {
        assertEquals(0, Scratchcards(listOf("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36")).sumPoints())
    }

    @Test fun testInput() {
        assertEquals(13, Scratchcards(listOf(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
        )).sumPoints())
    }

    @Test fun testInputCardCount() {
        assertEquals(30, Scratchcards(listOf(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
        )).sumCards())
    }
}