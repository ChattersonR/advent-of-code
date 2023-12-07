/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package adventofcode

import adventofcode.dayfive.SeedAlmanac
import adventofcode.dayfour.Scratchcards
import adventofcode.dayone.TrebuchetCalibration
import adventofcode.daysix.BoatRace
import adventofcode.daythree.GearRatios
import adventofcode.daytwo.CubeGame

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    println(App().greeting)

    val trebuchetCalibration = TrebuchetCalibration()
    val inputList: MutableList<String> = mutableListOf()
    App::class.java.getResource("/trebuchetCalibrationInput.txt")?.openStream()?.bufferedReader()?.lines()?.forEach {
        inputList.add(it)
    }
    val result = trebuchetCalibration.process(inputList.toList())
    println("Day 1 - Trebuchet Calibration: $result")

    val cubeInputList: MutableList<String> = mutableListOf()
    App::class.java.getResource("/cubeGameInput.txt")?.openStream()?.bufferedReader()?.lines()?.forEach {
        cubeInputList.add(it)
    }
    val cubeGame = CubeGame(cubeInputList)
    val cubeResult = cubeGame.sumPossibleGames(mapOf("red" to 12, "green" to 13, "blue" to 14))
    val cubePowerSum = cubeGame.powerSum()
    println("Day 2 - Cube Game Sum: $cubeResult")
    println("Day 2 - Cube Game Power Sum: $cubePowerSum")

    val gearRatio = GearRatios(readInputAsStringList("/gearRatioInput.txt"))
    val partsSum = gearRatio.sumPartNumbers()
    val gearRatioSum = gearRatio.sumGearRatio()
    println("Day 3 - Gear Ratio Part Sum: $partsSum")
    println("Day 3 - Gear Ratio Gear Sum: $gearRatioSum")

    val scratchcards = Scratchcards(readInputAsStringList("/scratchcardsInput.txt"))
    val pointSum = scratchcards.sumPoints()
    println("Day 4: Scratchcards Point Sum: $pointSum")
    println("Day 4: Scratchcards Card Count: ${scratchcards.sumCards()}")

    val almanac = SeedAlmanac(readInputAsStringList("/seedAlmanacInput.txt"))
    println("Day 5: Almanac Lowest Location: ${almanac.lowestLocation()}")
    println("Day 5: Almanac Lowest Location Ranges: ${almanac.lowestLocationOfRanges()}")

    val boatRaces = BoatRace(readInputAsStringList("/boatRaceInput.txt"))
    println("Day 6: Boat Race Margin: ${boatRaces.calculateMargin()}")
    println("Day 6: Boat Race Big Race: ${boatRaces.calculateSingleRace()}")
}

fun readInputAsStringList(path: String): List<String> {
    val returnList: MutableList<String> = mutableListOf()
    App::class.java.getResource(path)?.openStream()?.bufferedReader()?.lines()?.forEach {
        returnList.add(it)
    }

    return returnList
}