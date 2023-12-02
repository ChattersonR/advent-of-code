package adventofcode.dayone

class TrebuchetCalibration {
    val STRING_DIGITS: List<Pair<String, Int>> = listOf(
        Pair("one", 1),
        Pair("two", 2),
        Pair("three", 3),
        Pair("four", 4),
        Pair("five", 5),
        Pair("six", 6),
        Pair("seven",7),
        Pair("eight",8),
        Pair("nine",9)
    )

    fun process(input: List<String>): Int {
        var result: Int = 0

        for(i in input){
            result += extractNumber(i)
        }

        return result
    }

    private fun extractNumber(input: String): Int {
        var result: Int = 0
        for(i in 0..<input.length){
            val digit: Int? = convertDigit(input, i)
            if (digit != null) {
                result = 10*digit
                break
            }
        }

        for(i in (0..<input.length).reversed()){
            val digit: Int? = convertDigit(input, i)
            if (digit != null) {
                result += digit
                break
            }
        }

        return result
    }

    private fun convertDigit(input: String, index: Int): Int? {
        if(input[index].isDigit()){
            return input[index].digitToInt()
        } else {
            for(stringDigit in STRING_DIGITS) {
                if(input.length - index >= stringDigit.first.length && input.substring(index, index+stringDigit.first.length).equals(stringDigit.first)) {
                    return stringDigit.second
                }
            }
        }
        return null
    }
}