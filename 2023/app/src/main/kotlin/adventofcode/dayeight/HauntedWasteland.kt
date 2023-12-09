package adventofcode.dayeight

class HauntedWasteland(input: List<String>) {
    val instructions: String = input[0]
    val routes: Map<String, Pair<String, String>> = input.drop(2).associate {
        it.substring(0..2) to Pair(it.substring(7..9), it.substring(12..14))
    }

    fun stepsToEnd(start: String): Int {
        var currentNode: String = start
        var steps = 0

        while(!currentNode.endsWith('Z')) {
            val direction = instructions[steps % instructions.length]
            currentNode = if(direction == 'L') routes[currentNode]!!.first else routes[currentNode]!!.second
            steps++
        }

        return steps
    }

    fun Long.lcm(other: Long): Long =
        (this * other) / this.gcd(other)


    tailrec fun Long.gcd(other: Long): Long =
        if(other == 0L) this
        else other.gcd(this % other)

    fun ghostSteps(): Long =
        routes.keys
            .filter { it.endsWith("A") }
            .map { stepsToEnd(it).toLong() }
            .reduce { prev, next -> println("Prev: $prev, next: $next"); prev.lcm(next) }
}