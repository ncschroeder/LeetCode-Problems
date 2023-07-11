/*
https://leetcode.com/problems/find-peak-element/

A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array `nums`, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that `nums[-1]` = `nums[n]` = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.

Constraints:
- 1 <= `nums.length` <= 1000
- -231 <= `nums[i]` <= 231 - 1
- `nums[i]` != `nums[i + 1]` for all valid `i`.

Example:
Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
*/

/*
Use binary search. When given a start index and end index, first check if they are either the same or differ by 1.
If they're not, check the element at the index in the middle of those indices. If the element to the right of the
middle element is greater than the middle element, then there must be a peak *somewhere* to the right of that middle
element, so search that area. The same goes for the left if the element to the right is smaller but the element to
the left is greater. If the elements to the right and left are both smaller, then the middle element is a peak.
*/
fun findPeakElement(nums: IntArray): Int {
    tailrec fun findPeakElement(startIndex: Int, endIndex: Int): Int {
        when (endIndex - startIndex) {
            0 -> return startIndex
            1 -> return if (nums[startIndex] > nums[endIndex]) startIndex else endIndex
        }

        val midIndex: Int = (startIndex + endIndex) / 2
        val mid: Int = nums[midIndex]

        return when {
            nums[midIndex + 1] > mid -> findPeakElement(midIndex + 1, endIndex)
            nums[midIndex - 1] > mid -> findPeakElement(startIndex, midIndex - 1)
            else -> midIndex
        }
    }

    return findPeakElement(0, nums.lastIndex)
}