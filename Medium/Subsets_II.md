## Subsets II
### Difficulty: Medium
### [Link](https://leetcode.com/problems/subsets-ii/)

### Description

Given an integer array `nums` that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

The ["Subsets"](https://leetcode.com/problems/subsets/) problem is similar and the difference is that for that problem, you're given an integer array of *unique* integers.

### Example 1

#### Input
`nums = [1,2,2]`

#### Output
`[[],[1],[1,2],[1,2,2],[2],[2,2]]`

### Example 2

#### Input
`nums = [0]`

#### Output
`[[],[0]]`

### Constraints

- `1 <= nums.length <= 10`
- `-10 <= nums[i] <= 10`

### Solution

```kotlin
fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    /*
    Let subsetMaps be a set of maps. Each map represents a subset of nums
    and has keys for nums in nums and values for the counts of those nums.
    */
    val subsetMaps: MutableSet<Map<Int, Int>> = hashSetOf(emptyMap())
    
    for (num in nums) {
        val subsetMapsWithNumAdded =
            subsetMaps.map { map ->
                HashMap(map).also { it[num] = (it[num] ?: 0) + 1 }
            }
        
        subsetMaps.addAll(subsetMapsWithNumAdded)
    }
    
    return subsetMaps.map {
        buildList {
            for ((num, count) in it) {
                repeat(count) { add(num) }
            }
        }
    }
}
```