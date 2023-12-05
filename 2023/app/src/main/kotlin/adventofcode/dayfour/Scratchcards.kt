package adventofcode.dayfour

import kotlin.math.pow

class Scratchcards(input: List<String>) {
    val cards: List<Set<Int>>
    val numbers: List<Set<Int>>

    init {
        val mutableCards:MutableList<Set<Int>> = mutableListOf()
        val mutableNumbers:MutableList<Set<Int>> = mutableListOf()

        for(i in input.indices) {
            val split = input[i].split(":")[1].split("|")

            mutableCards.add(split[0].trim().split("\\s+".toRegex()).map { it.trim().toInt() }.toSet())
            mutableNumbers.add(split[1].trim().split("\\s+".toRegex()).map { it.trim().toInt() }.toSet())
        }

        cards = mutableCards
        numbers = mutableNumbers
    }

    fun sumPoints(): Int {
        return cards.foldIndexed(0) {index, accumulator, cardSet -> accumulator + tallyPoints(cardSet, numbers[index]) {count -> 2.0.pow(count-1).toInt()}}
    }

    private fun tallyPoints(card: Set<Int>, numbers: Set<Int>, operation: (count:Int) -> Int): Int {
        val intersect = card.intersect(numbers)
        return if(intersect.isEmpty()) 0 else operation(intersect.size)
    }

    fun sumCards(): Int {
        val cardCount: IntArray = IntArray(cards.size){1}
        cards.forEachIndexed { index, card ->
            for(i in index+1..(tallyPoints(card, numbers[index]) { count ->  count}+index).coerceAtMost(cards.size-1)) {
                cardCount[i]+=cardCount[index]
            }
        }

        return cardCount.sum()
    }
}