package adventofcode.daynine

class Mirage(input: List<String>) {
    val readings: List<List<Long>> = input.map { it.split(" ").filter { it.isNotEmpty() }.map { it.toLong() } }

    fun extrapolate (input: List<Long>, positionSelector: (input: List<Long>) -> Long, operation: (accumulator: Long, value: Long) -> Long): Long {
        if(input.isEmpty() || input.sum() == 0L) {
            return 0
        }
        val tmpList: MutableList<Long> = mutableListOf()
        for(i in 0..input.size - 2) {
            tmpList.add(input[i+1] - input[i])
        }
        return operation(extrapolate(tmpList, positionSelector, operation), positionSelector(input) )
    }

    fun extrapolatesSum(): Long {
        return readings.fold(0L) { acc, readings -> acc + extrapolate(readings, {it.last()}, { acc2,  value -> acc2 + value }) }
    }

    fun extrapolatesBeginningSum(): Long {
        return readings.fold(0L) { acc, readings -> acc + extrapolate(readings, {it.first()},{ acc2,  value -> value - acc2}) }
    }
}