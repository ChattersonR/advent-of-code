package adventofcode.daythree

import kotlin.test.Test
import kotlin.test.assertEquals
class GearRatiosTest {

    @Test fun regexTest(){
        val regex = """[\d]+""".toRegex()
        val matchResult = regex.findAll("467..114..")
        assertEquals(2, matchResult.count())
        matchResult.toList()[0]
        assertEquals(467, matchResult.toList()[0].value.toInt() )
        assertEquals(114, matchResult.toList()[1].value.toInt() )
    }

    @Test fun regexTest2(){
        val regex ="""[^\d^.]""".toRegex()
        val matchResult = regex.containsMatchIn("...*")
        assertEquals(true, matchResult)

        val matchResult2 = regex.containsMatchIn("....")
        assertEquals(false, matchResult2)

        val matchResult3 = regex.containsMatchIn(".123.")
        assertEquals(false, matchResult3)

        val matchResult4 = regex.containsMatchIn(".1&3.")
        assertEquals(true, matchResult4)
    }

    @Test fun testSumOfParts() {
        val testList = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        val testGears = GearRatios(testList)

        val sum = testGears.sumPartNumbers()
        assertEquals(4361, sum)
    }

    @Test fun testGearRatio(){
        val testList = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592*....",
            "......755.",
            "...$......",
            ".664*598..",
        )
        val testGears = GearRatios(testList)

        val sum = testGears.sumGearRatio()
        //assertEquals(467835, sum)
        assertEquals((467*35+664*598+592*755), sum)
    }
     fun extendedTest() {
        val testList = listOf(
            "311...672...34...391.....591......828.......................738....................223....803..472..................................714.840.",
            ".......*...........*.....*...........*........631%...703.......*..12....652.................*.$............368.769*148.................*....",
            "....411...........2....837.121........511.745...........*.48.422.@.........@.............311........887......*................457........595",
            "........*328...............&..........................144.*...................138............48.......*......682.........@...*.......777....",
            ".....144.....+........170...................207............813..../.&....139..*.....346........*..147..143.+.....78....536..79........*.....",
            "...........828...559.................181...%..........613.......10...928...*...993.+.........758.*.........471...#../...............573.....",
            "....................*164...132..........*........=.......*.................47.........186.........313..............411......................",
        )

        val testGears = GearRatios(testList)

        val sum = testGears.sumPartNumbers()

         val sum2 = testGears.sumPartNumbersOld()

        assertEquals((672+391+591+828+738+803+472+714+840
                +631+703+12+652+368+769+148
                +411+2+837+121+511+48+422+311+887+457
                +328+144+682
                +144+207+813+139+346+147+143+78+536+79
                +828+559+181+613+10+928+993+758+471+573
                +164+47+313+411
                ),
            sum2)
    }

    @Test fun edgeCase() {
        val testList = listOf(
            ".....",
            "/...&",
            "10.19",
            ".....",
        )

        val testGears = GearRatios(testList)

        val sum = testGears.sumPartNumbers()
        assertEquals(29, sum)
    }
}