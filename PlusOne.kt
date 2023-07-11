/*
https://leetcode.com/problems/plus-one/

You are given a large integer represented as an integer array `digits`, where each `digits[i]` is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

Example 1:
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].

Example 2:
Input: digits = [9]
Output: [1,0]
*/

fun plusOne(digits: IntArray): IntArray {
    /*
    Find the index of the last digit that isn't a 9. This is a digit that needs to be incremented.
    If the array only contains 9s then return a new IntArray that is 1 digit longer and has 1 as its
    1st digit and 0s for the rest.
    */
    val incrementIndex: Int = digits.indexOfLast { it != 9 }
    if (incrementIndex == -1) {
        return IntArray(size = digits.size + 1).also { it[0] = 1 }
    }

    // Increment that digit and set all following digits to 0
    digits[incrementIndex]++
    for (i in incrementIndex + 1..digits.lastIndex) {
        digits[i] = 0
    }

    return digits
}