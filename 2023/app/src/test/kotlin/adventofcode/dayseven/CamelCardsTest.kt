package adventofcode.dayseven

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CamelCardsTest {

    @Test fun testComparitor() {
        val camelCards = CamelCards(arrayListOf())

        val hand1 = CamelCards.Hand("12345", 1)
        val hand2 = CamelCards.Hand("11234", 1)
        val hand3 = CamelCards.Hand("11223", 1)
        val hand4 = CamelCards.Hand("11123", 1)
        val hand5 = CamelCards.Hand("11122", 1)
        val hand6 = CamelCards.Hand("11111", 1)
        val hand7 = CamelCards.Hand("23451", 1)

        assertTrue(camelCards.comparitor.compare(hand1, hand2) < 0, "hand1 smaller then hand2")
        assertTrue(camelCards.comparitor.compare(hand1, hand7) < 0, "hand1 smaller then hand7")
        assertTrue(camelCards.comparitor.compare(hand2, hand1) > 0, "hand2 bigger then hand1")
        assertTrue(camelCards.comparitor.compare(hand2, hand7) > 0, "hand2 bigger then hand7")
        assertTrue(camelCards.comparitor.compare(hand2, hand3) < 0, "hand2 smaller then hand3")
        assertTrue(camelCards.comparitor.compare(hand3, hand4) < 0, "hand3 smaller then hand4")
        assertTrue(camelCards.comparitor.compare(hand5, hand6) < 0, "hand5 smaller then hand6")
        assertTrue(camelCards.comparitor.compare(hand6, hand7) > 0, "hand6 bigger then hand7")
    }

    @Test fun testInput() {
        assertEquals(6440, CamelCards(listOf(
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483",
        )).totalWinnings())
    }

    @Test fun testInput2() {
        assertEquals(5905, CamelCards(listOf(
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483",
        )).totalWinningsWithJokers())
    }

}