package adventofcode.dayeight

import kotlin.test.Test
import kotlin.test.assertEquals

class HauntedWastelandTest {

    @Test fun testInput() {
        val testInput: List<String> = listOf(
            "LLR",
            "",
            "AAA = (BBB, BBB)",
            "BBB = (AAA, ZZZ)",
            "ZZZ = (ZZZ, ZZZ)",
        )

        assertEquals(6, HauntedWasteland(testInput).stepsToEnd("AAA"))
    }

    @Test fun testInput2() {
        val testInput: List<String> = listOf(
            "LR",
            "",
            "11A = (11B, XXX)",
            "11B = (XXX, 11Z)",
            "11Z = (11B, XXX)",
            "22A = (22B, XXX)",
            "22B = (22C, 22C)",
            "22C = (22Z, 22Z)",
            "22Z = (22B, 22B)",
            "XXX = (XXX, XXX)",
        )

        assertEquals(6, HauntedWasteland(testInput).ghostSteps())
    }
}