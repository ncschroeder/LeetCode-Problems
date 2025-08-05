## House Robber
### Difficulty: Medium
### [Link](https://leetcode.com/problems/house-robber/)

### Description

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array `nums` representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

### Example 1

#### Input
`nums = [1,2,3,1]`

#### Output
`4`

#### Explanation

Rob house 1 (money = 1) and then rob house 3 (money = 3).

Total amount you can rob = 1 + 3 = 4.

### Example 2

#### Input
`nums = [2,7,9,3,1]`

#### Output
`12`

#### Explanation

Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).

Total amount you can rob = 2 + 9 + 1 = 12.

### Constraints

- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 400`

### Solutions

If there's 1 house, we can, of course, only rob that house. Otherwise, we can start by robbing either the 1<sup>st</sup> or 2<sup>nd</sup> house. After we rob a house, if there are at least 3 houses to the right, we can rob either the 2<sup>nd</sup> or 3<sup>rd</sup> one. If there are 2, we can rob the 2<sup>nd</sup> one. Otherwise, we can't rob any more.

Use an int array called `maxMoneys` that has the same size as `nums`. The value at an index in `maxMoneys` will be set to the max money you can rob tonight if you start by robbing the house at that index in `nums`. The last 3 values will be set manually since they're the only ones where the corresponding house in `nums` doesn't have at least 3 houses to its right.

Below are 2 dynamic programming solutions.

#### Top-Down Solution :arrow_down:

```kotlin
fun rob(nums: IntArray): Int {
    if (nums.size == 1) return nums.first()
    
    val maxMoneys: Array<Int?> =
        arrayOfNulls<Int>(size = nums.size)
        .also {
            // Set last 3 values.
            it[it.lastIndex] = nums.last()
            it[it.lastIndex - 1] = nums[nums.lastIndex - 1]
            if (nums.size >= 3) {
                it[it.lastIndex - 2] = nums[nums.lastIndex - 2] + nums.last()
            }
        }
    
    fun getMaxMoney(index: Int): Int {
        // Return the memoized value if it exists.
        maxMoneys[index]?.let { return it }
        val maxMoney: Int = nums[index] + max(getMaxMoney(index + 2), getMaxMoney(index + 3))
        maxMoneys[index] = maxMoney
        return maxMoney
    }
    
    return max(getMaxMoney(0), getMaxMoney(1))
}
```

#### Bottom-Up Solution :arrow_up:

```kotlin
fun rob(nums: IntArray): Int {
    if (nums.size == 1) return nums.first()
    
    val maxMoneys =
        IntArray(size = nums.size)
        .also {
            // Set last 3 values.
            it[it.lastIndex] = nums.last()
            it[it.lastIndex - 1] = nums[nums.lastIndex - 1]
            if (nums.size >= 3) {
                it[it.lastIndex - 2] = nums[nums.lastIndex - 2] + nums.last()
            }
        }
    
    for (i: Int in maxMoneys.lastIndex - 3 downTo 0) {
        maxMoneys[i] = nums[i] + max(maxMoneys[i + 2], maxMoneys[i + 3])
    }
    
    return max(maxMoneys[0], maxMoneys[1])
}
```