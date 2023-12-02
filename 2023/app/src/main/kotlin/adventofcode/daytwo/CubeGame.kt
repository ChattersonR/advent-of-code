package adventofcode.daytwo

import java.io.File

class CubeGame(input: List<String>){

    /**
     * {
     *   1: [
     *     {
     *       red: 7
     *     }
     *   ]
     */
    val gameMap: Map<Int, List<Map<String, Int>>>;

    init {
        var tempMap: MutableMap<Int, List<Map<String, Int>>> = mutableMapOf()

        val regex = """Game ([\w]+): (.*)""".toRegex()

        //"Game 100: 7 red, 11 blue; 10 red, 5 blue, 1 green; 7 red, 1 green, 13 blue; 9 red; 9 red, 19 blue; 9 red, 9 blue"
        for(s in input) {
            val matchResult = regex.find(s)
            val (gameNumber, pulls) = matchResult!!.destructured

            val gameList: List<Map<String, Int>> = pulls.split(";")
                .map { mapString ->
                    mapString.split(",")
                        .associate {
                            val (key, value) = it.trim().split(" ")
                            value to key.toInt()
                        }
                }

            tempMap[gameNumber.toInt()] = gameList
        }

        gameMap = tempMap
    }

    fun sumPossibleGames(testMap: Map<String, Int>): Int {
        var accumulator: Int = 0
        gameMap.forEach { game ->
            if(testPulls(testMap, game.value)) {
                accumulator += game.key
            }
        }

        return accumulator
    }

    fun powerSum(): Int {
        var accumulator: Int = 0
        for(game in gameMap) {
            val minColors: Map<String, Int> = getMinColors(game.value)
            accumulator += minColors.values.reduce { accum, element -> accum * element}
        }

        return accumulator
    }

    private fun getMinColors(game: List<Map<String, Int>>): Map<String, Int> {
        val returnMap: MutableMap<String, Int> = mutableMapOf()
        for(pulls in game) {
            for(pull in pulls) {
                if(!returnMap.containsKey(pull.key) || returnMap[pull.key]!! < pull.value) {
                    returnMap[pull.key] = pull.value
                }
            }
        }
        return returnMap
    }

    private fun testPulls(testMap: Map<String, Int>, game: List<Map<String, Int>>): Boolean {
        val minColors: Map<String, Int> = getMinColors(game)
        for(entries in testMap) {
            if(!minColors.containsKey(entries.key) || minColors[entries.key]!! > entries.value) {
                return false
            }
        }

        return true
    }
}