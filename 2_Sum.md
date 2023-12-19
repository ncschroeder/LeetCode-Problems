## Two Sum :heavy_plus_sign:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/two-sum/)

### Description
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

### Example

#### Input
`nums = [2,7,11,15], target = 9`

#### Output
`[0,1]`

### Constraints
- <code>2 <= nums.length <= 10<sup>4</sup></code>
- <code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code>
- <code>-10<sup>9</sup> <= target <= 10<sup>9</sup></code>
- Only one valid answer exists.

### Follow-Up
Can you come up with an algorithm that is less than $O(n^2)$ time complexity? Yes.

### Simple $O(n^2)$ Solution

```kotlin
fun twoSum(nums: IntArray, target: Int): IntArray {
    for (i in 0 until nums.lastIndex) {
        val intToFind = target - nums[i]
        for (j in i + 1..nums.lastIndex) {
            if (nums[j] == intToFind) {
                return intArrayOf(i, j)
            }
        }
    }

    throw Exception("No answer found")
}
```

### $O(n)$ Solution

```kotlin
fun twoSum(nums: IntArray, target: Int): IntArray {

    if (target % 2 == 0) {
        val halfOfTarget: Int = target / 2
        val halfOfTargetIndices: List<Int> =
            nums.indices.filter { nums[it] == halfOfTarget }
    
        if (halfOfTargetIndices.size == 2) {
            return halfOfTargetIndices.toIntArray()
        }
    }
    
    val numsAndIndices: Map<Int, Int> =
        nums.withIndex().associate { (index, num) -> num to index }
    
    for ((index1, num) in nums.withIndex()) {
        val numToFind = target - num
        val index2: Int? = numsAndIndices[numToFind]

        /*
        If the target is even and target / 2 is a number in nums, then numToFind will be the same as target / 2
        so index2 will be equal to index1.
        */
        if (index2 != null && num != numToFind) {
            return intArrayOf(index1, index2)
        }
    }

    throw Exception("No answer found")
}
```