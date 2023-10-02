/*
https://leetcode.com/problems/house-robber/

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Examples:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
*/

// Bottom-up dynamic programming solution
fun rob(nums: IntArray): Int {
    when (nums.size) {
        1 -> return nums.first()
        2 -> return nums.max()
        3 -> return Math.max(nums.first() + nums.last(), nums[1])
    }

    /*
    Let maxMoneys be an array that starts off with all 0s and has its elements get changed from back to front.
    The new values are the max money amounts that you can get from robbing the house at that index and all possible
    combinations of houses after that.
    */
    val maxMoneys: IntArray =
        IntArray(size = nums.size)
        .also {
            // Set last 3 elements
            it[it.lastIndex] = nums.last()
            it[it.lastIndex - 1] = nums[nums.lastIndex - 1]
            it[it.lastIndex - 2] = nums[nums.lastIndex - 2] + nums.last()
        }

    // Find out the money at the house at index i and the max of the money you can get from choosing to rob
    // the house either 2 or 3 indices to the right.
    for (i in maxMoneys.lastIndex - 3 downTo 0) {
        maxMoneys[i] = nums[i] + Math.max(maxMoneys[i + 2], maxMoneys[i + 3])
    }

    // We can start at either the 1st or 2nd house.
    return Math.max(maxMoneys.first(), maxMoneys[1])
}