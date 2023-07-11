/*
https://leetcode.com/problems/majority-element/

Given an array `nums` of size `n`, return the majority element.

The majority element is the element that appears more than ⌊`n` / 2⌋ times. You may assume that the majority element always exists in the array.
*/

fun majorityElement(nums: IntArray): Int {
    // Let numCounts be a Map where the keys are the ints in nums and the values are the counts for how many times
    // that int appears in nums
    val numCounts = HashMap<Int, Int>()
    for (n in nums) {
        numCounts[n] = (numCounts[n] ?: 0) + 1
    }

    val nDiv2: Int = nums.size / 2
    return numCounts.asIterable()
        .first { (_, count) -> count > nDiv2 }
        .let { (num, _) -> num }
}