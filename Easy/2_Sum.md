## 2 Sum :heavy_plus_sign:
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

I got a very similar problem to this in an interview. I had to find the pairs of indices and print them instead of returning an int array and there was a possibility of there being multiple solutions. I used the JavaScript equivalent of this solution for that.

```kotlin
fun twoSum(nums: IntArray, target: Int): IntArray {
    for (i: Int in 0 until nums.lastIndex) {
        val numToFind: Int = target - nums[i]
        for (j: Int in i + 1..nums.lastIndex) {
            if (nums[j] == numToFind) {
                return intArrayOf(i, j)
            }
        }
    }
    
    throw Exception("No solution found")
}
```

### $O(n)$ Solution

If `target` is even, then it's possible that there are 2 numbers in `nums` that are the same that sum to `target`. Otherwise, there can't be duplicates of numbers that are part of the solution because if there were, there would be more than 1 valid solution. For example, if `nums = [1, 2, 2]` and `target = 3`, then `[0, 1]` and `[0, 2]` would be valid solutions. The description says we can assume there's only 1 solution.

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
    
    /*
    Let numsAndIndices be a map where the keys are the numbers in nums and the values are the indices
    of those numbers. We can map each number to a single index since the numbers in the solution will
    only have a single index. If there are duplicates of numbers that aren't part of the solution,
    then the associate function will map those numbers to the last index they were found at.
    */
    val numsAndIndices: Map<Int, Int> =
        nums.withIndex().associate { (index: Int, num: Int) -> num to index }
    
    for ((index1: Int, num: Int) in nums.withIndex()) {
        val index2: Int? = numsAndIndices[target - num]
        
        /*
        If the target is even and target / 2 is a number in nums, then when we get to the iteration where num
        is equal to target / 2, num will also be equal to target - num so index1 will be equal to index2.
        */
        if (index2 != null && index1 != index2) {
            return intArrayOf(index1, index2)
        }
    }
    
    throw Exception("No solution found")
}
```