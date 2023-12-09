package adventofcode.dayseven

import kotlin.math.pow

class CamelCards (input: List<String>) {
    data class Hand(val handString: String, val bid: Int) {
        val handValue: Int
        val handValueJoker: Int

        init {
            val tempMap:MutableMap<Char, Int> = mutableMapOf()
            handString.associateWithTo(tempMap){ tempMap.getOrDefault(it, 0) + 1 }
            handValue = tempMap.values.fold(0){ acc, i -> acc + 2.0.pow((i - 1)*2).toInt() }

            val numJokers: Int = tempMap.getOrDefault('J', 0)
            tempMap.remove('J')
            if(tempMap.isEmpty()) {
                //Edge case for only Jokers
                handValueJoker = 2.0.pow((5 - 1) * 2).toInt()
            } else {
                handValueJoker = tempMap.values.sorted().reversed().foldIndexed(0) { index, acc, value ->
                    val plusJoker: Int = if (index == 0) numJokers else 0
                    acc + 2.0.pow((value + plusJoker - 1) * 2).toInt()
                }
            }
        }

        override fun toString(): String {
            return "Hand(handString: $handString, handValue: $handValue, handValueWithJoker:$handValueJoker)"
        }
    }
    private val cardValues: Map<Char, Int> = mapOf(
        '1' to 1,
        '2' to 2,
        '3' to 3,
        '4' to 4,
        '5' to 5,
        '6' to 6,
        '7' to 7,
        '8' to 8,
        '9' to 9,
        'T' to 10,
        'J' to 11,
        'Q' to 12,
        'K' to 13,
        'A' to 14
    )

    private val cardValuesJoker: Map<Char, Int> = mapOf(
        'J' to 0,
        '1' to 1,
        '2' to 2,
        '3' to 3,
        '4' to 4,
        '5' to 5,
        '6' to 6,
        '7' to 7,
        '8' to 8,
        '9' to 9,
        'T' to 10,
        'Q' to 12,
        'K' to 13,
        'A' to 14
    )

    val comparitor = compareBy<Hand>(
        {it.handValue},
        {cardValues[it.handString[0]]},
        {cardValues[it.handString[1]]},
        {cardValues[it.handString[2]]},
        {cardValues[it.handString[3]]},
        {cardValues[it.handString[4]]})

    val comparitorJoker = compareBy<Hand>(
        {it.handValueJoker},
        {cardValuesJoker[it.handString[0]]},
        {cardValuesJoker[it.handString[1]]},
        {cardValuesJoker[it.handString[2]]},
        {cardValuesJoker[it.handString[3]]},
        {cardValuesJoker[it.handString[4]]})

    val hands: List<Hand> = input.map {
        val split = it.split(" ")
        Hand(split[0], split[1].toInt())
    }.sortedWith(comparitor)

    val handsWithJokers: List<Hand> = input.map {
        val split = it.split(" ")
        Hand(split[0], split[1].toInt())
    }.sortedWith(comparitorJoker)

    fun totalWinnings(): Long {
        return hands.foldIndexed(0){ index, acc, hand -> acc + (hand.bid * (index + 1)) }
    }

    fun totalWinningsWithJokers (): Long {
        return handsWithJokers.foldIndexed(0){ index, acc, hand -> acc + (hand.bid * (index + 1)) }
    }
}