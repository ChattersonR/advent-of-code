package adventofcode.daytwo

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CubeGameTest {

    @Test fun regexCaptureGroups() {
        val regex = """Game ([\w]+): (.*)""".toRegex()
        val matchResult = regex.find("Game 100: 7 red, 11 blue; 10 red, 5 blue, 1 green; 7 red, 1 green, 13 blue; 9 red; 9 red, 19 blue; 9 red, 9 blue")

        val (gameNumber, pulls) = matchResult!!.destructured
        assertEquals("100", gameNumber)
        assertEquals("7 red, 11 blue; 10 red, 5 blue, 1 green; 7 red, 1 green, 13 blue; 9 red; 9 red, 19 blue; 9 red, 9 blue", pulls)

        val gameList: List<Map<String, Int>> = pulls.split(";")
            .map { mapString ->
                mapString.split(",")
                    .associate {
                        val (key, value) = it.trim().split(" ")
                        value to key.toInt()
                    }
            }

        println(gameList)
    }

    @Test fun testInitialization() {
        val cubeGameTest: CubeGame = CubeGame(
            listOf(
                "Game 1: 2 blue, 4 green; 7 blue, 1 red, 14 green; 5 blue, 13 green, 1 red; 1 red, 7 blue, 11 green",
                "Game 2: 6 blue, 3 green; 4 red, 1 green, 7 blue; 2 green",
                "Game 3: 4 blue, 3 red; 2 blue, 4 red, 7 green; 1 blue, 6 red, 7 green; 5 green, 10 blue; 9 green, 1 blue, 6 red; 8 blue, 1 red, 12 green",
                "Game 4: 15 blue, 4 green, 5 red; 2 red, 2 green, 5 blue; 3 green, 13 blue; 17 blue, 1 green, 5 red",
                "Game 5: 11 green, 4 red, 3 blue; 8 blue, 6 green; 8 green, 2 red, 9 blue; 4 red, 16 blue; 8 blue, 10 red, 6 green; 9 blue, 3 red, 10 green",
                "Game 6: 4 green, 9 red, 2 blue; 7 red, 2 green, 15 blue; 13 red, 2 green, 6 blue; 5 green, 7 blue, 6 red; 19 red, 15 blue, 4 green",
                "Game 7: 12 blue, 5 red; 5 green, 6 blue; 5 red, 15 blue; 5 blue, 5 red, 5 green; 1 green, 11 blue, 2 red",
                "Game 8: 6 red, 11 green; 5 red, 2 blue, 7 green; 7 red, 6 green"
            )
        )

        assertEquals(setOf(1, 2, 3, 4, 5, 6, 7, 8), cubeGameTest.gameMap.keys)

        val testGame = cubeGameTest.gameMap.get(4)
        assertEquals(4, testGame?.size)
        //15 blue, 4 green, 5 red
        assertEquals(mapOf("blue" to 15, "green" to 4, "red" to 5), testGame?.get(0))
        //2 red, 2 green, 5 blue
        assertEquals(mapOf("blue" to 5, "green" to 2, "red" to 2), testGame?.get(1))
        //3 green, 13 blue
        assertEquals(mapOf("green" to 3, "blue" to 13), testGame?.get(2))
        //17 blue, 1 green, 5 red
        assertEquals(mapOf("blue" to 17, "green" to 1, "red" to 5), testGame?.get(3))
    }

    @Test fun testsProcessor() {
        val cubeGameTest: CubeGame = CubeGame(
            listOf(
                "Game 1: 2 blue, 4 green; 7 blue, 1 red, 14 green; 5 blue, 13 green, 1 red; 1 red, 7 blue, 11 green",
                "Game 2: 6 blue, 3 green; 4 red, 1 green, 7 blue; 2 green",
                "Game 3: 4 blue, 3 red; 2 blue, 4 red, 7 green; 1 blue, 6 red, 7 green; 5 green, 10 blue; 9 green, 1 blue, 6 red; 8 blue, 1 red, 12 green",
                "Game 4: 15 blue, 4 green, 5 red; 2 red, 2 green, 5 blue; 3 green, 13 blue; 17 blue, 1 green, 5 red",
                "Game 5: 11 green, 4 red, 3 blue; 8 blue, 6 green; 8 green, 2 red, 9 blue; 4 red, 16 blue; 8 blue, 10 red, 6 green; 9 blue, 3 red, 10 green",
                "Game 6: 4 green, 9 red, 2 blue; 7 red, 2 green, 15 blue; 13 red, 2 green, 6 blue; 5 green, 7 blue, 6 red; 19 red, 15 blue, 4 green",
                "Game 7: 12 blue, 5 red; 5 green, 6 blue; 5 red, 15 blue; 5 blue, 5 red, 5 green; 1 green, 11 blue, 2 red",
                "Game 8: 6 red, 11 green; 5 red, 2 blue, 7 green; 7 red, 6 green"
            )
        )

        val sum = cubeGameTest.sumPossibleGames(mapOf("blue" to 10))

        assertEquals((1+2+3+8), sum)
    }

    @Test fun powerSumTest() {
        val cubeGameTest: CubeGame = CubeGame(
            listOf(
                "Game 1: 2 blue, 4 green; 7 blue, 1 red, 14 green; 5 blue, 13 green, 1 red; 1 red, 7 blue, 11 green",

            )
        )

        val sum = cubeGameTest.powerSum()

        assertEquals((7*14*1), sum)
    }
    @Test fun powerSumTest1() {
        val cubeGameTest: CubeGame = CubeGame(
            listOf(
                "Game 1: 2 blue, 4 green; 7 blue, 1 red, 14 green; 5 blue, 13 green, 1 red; 1 red, 7 blue, 11 green",
                "Game 2: 6 blue, 3 green; 4 red, 1 green, 7 blue; 2 green",
                )
        )

        val sum = cubeGameTest.powerSum()

        assertEquals(((7*14*1) + (7*3*4)), sum)
    }
}