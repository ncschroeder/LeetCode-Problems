## 2 Out of 3
### Difficulty: Easy
### [Link](https://leetcode.com/problems/two-out-of-three/)

### Description

Given three integer arrays `nums1`, `nums2`, and `nums3`, return a distinct array containing all the values that are present in at least two out of the three arrays. You may return the values in any order.

### Example 1

#### Input
`nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]`

#### Output
`[3,2]`

#### Explanation

The values that are present in at least two arrays are:
- 3, in all three arrays.
- 2, in `nums1` and `nums2`.

### Example 2

#### Input
`nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]`

#### Output
`[]`

#### Explanation

No value is present in at least two arrays.

### Constraints

- `1 <= nums1.length, nums2.length, nums3.length <= 100`
- `1 <= nums1[i], nums2[j], nums3[k] <= 100`

### Solution

The 3 combinations of 2 arrays are 1 and 2, 1 and 3, and 2 and 3. Let `majorityNums` be used for the nums that are in at least 2 of the arrays. It'll start off with the nums that are in `nums1` and either `nums2` or `nums3` or both. The nums that are in `nums2` and `nums3` will get added after.

```kotlin
fun twoOutOfThree(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int> {
    val set2: Set<Int> = nums2.toHashSet()
    val set3: Set<Int> = nums3.toHashSet()
    val majorityNums: MutableSet<Int> = 
        nums1.filterTo(HashSet()) { it in set2 || it in set3 }
    
    return (
        set2
        .filterTo(majorityNums) { it in set3 }
        .toList()
    )
}
```