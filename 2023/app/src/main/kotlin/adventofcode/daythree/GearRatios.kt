package adventofcode.daythree

class GearRatios(inputLines: List<String>) {

    val symbolRegex ="""[^\d^.]""".toRegex()
    val digitRegex = """[\d]+""".toRegex()

    val engineInput: List<String> = inputLines

    fun sumPartNumbersOld(): Int {
        var accumulator = 0


        for (i in engineInput.indices) {
            val line: String = engineInput[i]

            val matchResult: Sequence<MatchResult> = digitRegex.findAll(line)

            for (result in matchResult) {
                accumulator += checkForSymbols(i, result.groups[0])
            }
        }

        return accumulator
    }

    fun sumPartNumbers(): Int {
        val adjacencyMap = buildAdjacencyMap()

        var accumulator: Int = 0
        adjacencyMap.forEach { (_, values) ->
            values.forEach { adjacentValues ->
                adjacentValues.forEach { accumulator += it }
            }
        }

        return accumulator
    }

    fun sumGearRatio(): Int {
        val adjacencyMap = buildAdjacencyMap()

        var accumulator: Int = 0
        adjacencyMap["*"]?.forEach { adjacentValues ->
            if(adjacentValues.size == 2) {
                accumulator += adjacentValues.reduce { accum, value -> accum*value }
            }
        }

        return accumulator
    }
    private fun buildAdjacencyMap(): MutableMap<String, MutableList<List<Int>>> {
        val adjacencyMap: MutableMap<String, MutableList<List<Int>>> = mutableMapOf()

        for (i in engineInput.indices) {
            val line: String = engineInput[i]

            val matchResult: Sequence<MatchResult> = symbolRegex.findAll(line)

            for (result in matchResult) {
                val adjacentNumbers = getAdjacentNumbers(i, result.groups[0])
                if(adjacentNumbers != null) {
                    if(!adjacencyMap.containsKey(adjacentNumbers.first)) {
                        adjacencyMap[adjacentNumbers.first] = mutableListOf(adjacentNumbers.second)
                    } else {
                        adjacencyMap[adjacentNumbers.first]?.add(adjacentNumbers.second)
                    }
                }
            }
        }

        return adjacencyMap
    }



    private fun getAdjacentNumbers(lineIndex: Int, matchResult: MatchGroup?): Pair<String, List<Int>>? {
        if(matchResult == null) {
            return null
        }

        val adjacentValues: MutableList<Int> = mutableListOf()

        val digitRegex = """[\d]+""".toRegex()
        for ( i in 0.coerceAtLeast(lineIndex - 1)..(engineInput.size - 1).coerceAtMost(lineIndex + 1)) {
            val subSequence: CharSequence = engineInput[i].subSequence(
                0.coerceAtLeast(matchResult.range.first - 1),
                (matchResult.range.last + 2).coerceAtMost(engineInput[i].length)
            )


            for (result in digitRegex.findAll(subSequence)) {
                var range: IntRange = result.range.first+matchResult.range.first..result.range.last+matchResult.range.first
                while(range.last < engineInput[i].length-1
                    && digitRegex.matches(engineInput[i].subSequence(range.first, range.last + 1))) {
                    range = range.first..range.last+1
                }

                while(range.first > 0
                    && digitRegex.matches(engineInput[i].subSequence(range.first-1, range.last))) {
                    range = range.first-1..range.last
                }
                adjacentValues.add(engineInput[i].subSequence(range.first, range.last).toString().toInt())
            }
        }

        return Pair(matchResult.value, adjacentValues.toList());
    }

    private fun checkForSymbols(lineIndex: Int, matchResult: MatchGroup?): Int {
        if(matchResult == null) {
            return 0
        }

        for ( i in 0.coerceAtLeast(lineIndex - 1)..(engineInput.size - 1).coerceAtMost(lineIndex + 1)) {
            val subSequence: CharSequence = engineInput[i].subSequence(
                0.coerceAtLeast(matchResult.range.first - 1),
                (matchResult.range.last + 2).coerceAtMost(engineInput[i].length - 1)
            )

            if(symbolRegex.containsMatchIn(subSequence)) {
                return matchResult.value.toInt()
            }
        }

        return 0

    }
}