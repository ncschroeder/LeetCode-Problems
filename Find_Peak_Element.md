## Find Peak Element :mountain:
### Diffculty: Medium
### [Link](https://leetcode.com/problems/find-peak-element/)

### Description
A *peak element* is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array `nums`, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that `nums[-1] = nums[n] = -∞`. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in $O(log(n))$ time.

### Example 1

#### Input
`nums = [1,2,3,1]`

#### Output
`2`

#### Explanation
3 is a peak element and your function should return the index number 2.

### Example 2

#### Input
`nums = [1,2,1,3,5,6,4]`

#### Output
`5`

#### Explanation
Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.

### Constraints
- `1 <= nums.length <= 1000`
- `-2^31 <= nums[i] <= 2^31 - 1`
- `nums[i] != nums[i + 1]` for all valid `i`.

### Solutions

To solve this, do a binary search. When given a start and end index, first check if they are either the same or differ by 1. If they're not, check the element at the index in the middle of those indices and the element before that. If the element before it is greater, there must be a peak element *somewhere* from the start index to the mid index - 1. Otherwise, the middle element is greater because of the 3<sup>rd</sup> constraint so there must be a peak element *somewhere* from the mid index to the last index.

####  Tail Recursive Solution

Here's a [link to the tail recursive functions section of the *Kotlin Docs* article on functions](https://kotlinlang.org/docs/functions.html#tail-recursive-functions).

```kotlin
fun findPeakElement(nums: IntArray): Int {
    tailrec fun findPeakElement(startIndex: Int, endIndex: Int): Int =
        when (endIndex - startIndex) {
            0 -> startIndex
            
            1 -> if (nums[startIndex] > nums[endIndex]) startIndex else endIndex
            
            else -> {
                val midIndex: Int = (startIndex + endIndex) / 2
                
                if (nums[midIndex - 1] > nums[midIndex]) {
                    findPeakElement(startIndex, midIndex - 1)
                } else {
                    findPeakElement(midIndex, endIndex)
                }
            }
        }

    return findPeakElement(0, nums.lastIndex)
}
```

####  Iterative Solution

```kotlin
fun findPeakElement(nums: IntArray): Int {
    var startIndex = 0
    var endIndex: Int = nums.lastIndex

    while (true) {
        when (endIndex - startIndex) {
            0 -> return startIndex
            
            1 -> return if (nums[startIndex] > nums[endIndex]) startIndex else endIndex
        }

        val midIndex: Int = (startIndex + endIndex) / 2

        if (nums[midIndex - 1] > nums[midIndex]) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex
        }
    }
}
```