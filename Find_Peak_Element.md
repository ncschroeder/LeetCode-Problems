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

3 is a peak element and your function should return the index number `2`.

### Example 2

#### Input
`nums = [1,2,1,3,5,6,4]`

#### Output
`5`

#### Explanation

Your function can return either index number `1` where the peak element is 2, or index number `5` where the peak element is 6.

### Constraints
- `1 <= nums.length <= 1000`
- <code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code>
- `nums[i] != nums[i + 1]` for all valid `i`.

### Solutions

To solve this, do a binary search. When given a first and last index, first check if they're the same. If they are then that index is a peak index. Otherwise, check the number at the index in the middle of those indices and the number after that. If the number after it is greater, there must be a peak element *somewhere* from the mid index + 1 to the last index. Otherwise, the mid number is greater because of the 3<sup>rd</sup> constraint so there must be a peak element *somewhere* from the first index to the mid index.

For finding the mid index, we can use `(firstIndex + lastIndex) / 2` since the 1<sup>st</sup> constraint says that the max length of `nums` is 1,000. When finding the mid index, the highest possible values for `firstIndex` and `lastIndex` are 998 and 999, respectively. $998 + 999 = 1,997$, which is way lower than the `Int` max value, which is 2 billion something. There are other binary search problems on LeetCode where I tried finding a mid value using the same arithmetic and this caused problems with overflow because of the constraints of those problems and because I was using `Int`s for the arithmetic. The simplest way to fix those problems is to use `Long`s for the arithmetic, but we don't need to do that for this problem.

#### Refactored Tail Recursive Solution

Info about tail recursive functions can be found in the Kotlin Docs [here](https://kotlinlang.org/docs/functions.html#tail-recursive-functions).

```kotlin
fun findPeakElement(nums: IntArray): Int {
    tailrec fun findPeakElement(firstIndex: Int, lastIndex: Int): Int =
        if (firstIndex == lastIndex) firstIndex
        else {
            val midIndex: Int = (firstIndex + lastIndex) / 2
                
            if (nums[midIndex + 1] > nums[midIndex]) {
                findPeakElement(midIndex + 1, lastIndex)
            } else {
                findPeakElement(firstIndex, midIndex)
            }
        }

    return findPeakElement(0, nums.lastIndex)
}
```

#### Iterative Solution

```kotlin
fun findPeakElement(nums: IntArray): Int {
    var firstIndex = 0
    var lastIndex: Int = nums.lastIndex

    while (firstIndex != lastIndex) {
        val midIndex: Int = (firstIndex + lastIndex) / 2

        if (nums[midIndex + 1] > nums[midIndex]) {
            firstIndex = midIndex + 1
        } else {
            lastIndex = midIndex
        }
    }

    return firstIndex
}
```

#### Original Tail Recursive Solution

```kotlin
fun findPeakElement(nums: IntArray): Int {

    tailrec fun search(startIndex: Int, endIndex: Int): Int {
        if (startIndex == endIndex) {
            return startIndex
        }

        if (startIndex + 1 == endIndex) {
            return if (nums[startIndex] > nums[endIndex]) startIndex else endIndex
        }

        val midIndex = (startIndex + endIndex) / 2
        val mid = nums[midIndex]
        if (nums[midIndex + 1] > mid) {
            return search(midIndex + 1, endIndex)
        }

        if (nums[midIndex - 1] > mid) {
            return search(startIndex, midIndex - 1)
        }

        return midIndex
    }

    return search(0, nums.lastIndex)
}
```