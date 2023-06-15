/*
https://leetcode.com/problems/summary-ranges/

You are given a sorted unique integer array `nums`.

A range [a,b] is the set of all integers from a to b (inclusive).

Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of `nums` is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in `nums`.

Each range [a,b] in the list should be output as:
- "a->b" if a != b
- "a" if a == b

Example 1:
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]

Example 2:
Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
*/

fun summaryRanges(nums: IntArray): List<String> {
    if (nums.isEmpty()) {
        return emptyList()
    }

    val ranges = mutableListOf<String>()
    var rangeStart: Int = nums.first()
    var prevNum: Int = rangeStart

    fun addRange() {
        ranges.add(if (rangeStart == prevNum) rangeStart.toString() else "$rangeStart->$prevNum")
    }

    // Iterate through nums starting at the 2nd num
    for (n in nums.asSequence().drop(1)) {
        if (prevNum + 1 != n) {
            addRange()
            rangeStart = n
        }
        prevNum = n
    }

    addRange()
    return ranges
}