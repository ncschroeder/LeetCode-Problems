/*
https://leetcode.com/problems/jump-game-ii/

You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
0 <= j <= nums[i] and i + j < n.
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

Example:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
*/

// Bottom-up dynamic programming solution
fun jump(nums: IntArray): Int {
    // Let minJumps be a nullable int array that starts off with nulls and has its elements changed from back to front.
    // The new values will be null if the end can't be reached from that index or the min number of jumps from that index to reach the end.
    val minJumps: Array<Int?> =
        arrayOfNulls<Int>(size = nums.size)
        .also {
            // Set last element to 0
            it[it.lastIndex] = 0
        }

    for (i in minJumps.lastIndex - 1 downTo 0) {
        minJumps[i] =
            minJumps
            .sliceArray(i + 1..Math.min(i + nums[i], minJumps.lastIndex))
            .filterNotNull()
            .minOrNull()
            ?.plus(1)
    }

    // Do a non-null assertion since we're guaranteed that the 1st element can reach the end.
    return minJumps.first()!!
}