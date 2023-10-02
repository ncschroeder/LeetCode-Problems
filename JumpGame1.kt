/*
https://leetcode.com/problems/jump-game/

You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.

Examples:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
*/

// Bottom-up dynamic programming solution
fun canJump(nums: IntArray): Boolean {
    // Let canReachEndBools be a boolean array that starts off with all falses and has its elements get changed
    // from back to front. The new values will be true if that index can reach the end and false otherwise.
    val canReachEndBools: BooleanArray =
        BooleanArray(size = nums.size)
        .also {
            // Set last element to true
            it[it.lastIndex] = true
        }

    for (i in canReachEndBools.lastIndex - 1 downTo 0) {
        canReachEndBools[i] =
            canReachEndBools
            .sliceArray(i + 1..Math.min(i + nums[i], canReachEndBools.lastIndex))
            .any { it }
    }

    return canReachEndBools.first()
}