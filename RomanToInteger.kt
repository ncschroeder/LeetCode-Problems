/*
https://leetcode.com/problems/roman-to-integer/

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is
before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
- I can be placed before V (5) and X (10) to make 4 and 9. 
- X can be placed before L (50) and C (100) to make 40 and 90. 
- C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer.
*/

fun romanToInt(s: String): Int {
    // First, identify the subtraction sequences in s. Remove them and increment subtractionSequencesSum by their values.
    // Then, get the sum of the corresponding values for the numerals in updatedNumeral and add that to substractionSequencesSum.
    var updatedNumeral = s
    var subtractionSequencesSum = 0

    arrayOf(
        Pair("IV", 4),
        Pair("IX", 9),
        Pair("XL", 40),
        Pair("XC", 90),
        Pair("CD", 400),
        Pair("CM", 900)
    )
    .forEach { (subtractionSequence, value) ->
        if (subtractionSequence in updatedNumeral) {
            subtractionSequencesSum += value
            updatedNumeral = updatedNumeral.replace(subtractionSequence, "")
        }
    }

    val numeralValues =
        mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )

    return subtractionSequencesSum + updatedNumeral.sumOf(numeralValues::getValue)
}