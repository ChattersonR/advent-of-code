package adventofcode.daysix

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Let t be the total time
 * let th be the time the button was held
 * let d be the record distance
 *
 * The equation for calculating the distance traveled by the boat is th * (t - th).
 * This turns out to be a quadratic equation, y = -th^2 + t*th.
 *
 * We want the bounds between the two intersection points with line y = d
 *
 * If we subtract the record distance from the equation -th^2 + t*th - d, then the intersection points will lie
 * on the  origin y=0, allowing us to use the quadratic formulae to  solve for the two points of intersection.
 */
class BoatRace(input: List<String>) {
    data class Race(val duration: Double, val record: Long)
    val races: List<Race>
    val bigRace: Race

    init{
        val times = input[0].substringAfter(":").trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }.map { it.toDouble() }
        val records = input[1].substringAfter(":").split("\\s+".toRegex()).filter { it.isNotEmpty() }.map { it.toLong() }

        val mutableRaces: MutableList<Race> = mutableListOf()
        for(i in times.indices){
            mutableRaces+=Race(times[i], records[i])
        }

        races = mutableRaces

        val bigTime = input[0].substringAfter(":").trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }.fold("") { acc, s -> acc + s }.toDouble()
        val bigRecord = input[1].substringAfter(":").split("\\s+".toRegex()).filter { it.isNotEmpty() }.fold("") { acc, s -> acc + s }.toLong()
        bigRace = Race(bigTime, bigRecord)
    }

    fun calculateMargin(): Long{
        val intersections = races.map{ calculateIntersections(it) }
        return intersections.fold(1) { acc, range -> acc * (range.last - range.first + 1) }
    }

    fun calculateSingleRace(): Long {
        val intersection = calculateIntersections(bigRace)
        return intersection.last - intersection.first + 1L
    }

    private fun calculateIntersections(race: Race): LongRange {
        val lowerBound = (-1 * race.duration + sqrt(race.duration.pow(2)+(4*-1*race.record)))/-2
        val upperBound = (-1 * race.duration - sqrt(race.duration.pow(2)+(4*-1*race.record)))/-2

        return floor(lowerBound+1).toLong()..ceil(upperBound-1).toLong()
    }
}