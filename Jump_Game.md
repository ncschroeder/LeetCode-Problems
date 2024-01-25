## Jump Game :frog: :kangaroo:
### Difficulty: Medium
### [Link](https://leetcode.com/problems/jump-game/)

### Description

You are given an integer array `nums`. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return `true` if you can reach the last index, or `false` otherwise.

### Example 1

#### Input
`nums = [2,3,1,1,4]`

#### Output
`true`

#### Explanation

Jump 1 step from index 0 to 1, then 3 steps to the last index.

### Example 2

#### Input
`nums = [3,2,1,0,4]`

#### Output
`false`

#### Explanation

You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

### Constraints
- <code>1 <= nums.length <= 10<sup>4</sup></code>
- <code>0 <= nums[i] <= 10<sup>5</sup></code>

### Solutions

An index can reach the last index *directly* if it is the last index or if the index plus its max jump length is >= the last index. An index can reach the last index *indirectly* if it can reach any indices that can reach the last index, either directly or indirectly. The exceptions to this are that the last index and the 2<sup>nd</sup> to last index can't indirectly reach the last index.


Use a boolean array called `canReachLastBools` that has the same size as `nums`. The value at an index in `canReachLastBools` will be set to `true` if that index can reach the last index and `false` if it can't.


Below are 2 dynamic programming solutions.

#### Top-Down Solution :arrow_down:

```kotlin
fun canJump(nums: IntArray): Boolean {
    val canReachLastBools: Array<Boolean?> =
        arrayOfNulls<Boolean>(size = nums.size)
        .also {
            // Set last value.
            it[it.lastIndex] = true
        }

    fun canReachLast(index: Int): Boolean {
        // Return the memoized value if it exists.
        canReachLastBools[index]?.let { return it }

        val canReachLastBool: Boolean =
            (index + 1..min(index + nums[index], nums.lastIndex))
            .any(::canReachLast)

        canReachLastBools[index] = canReachLastBool
        return canReachLastBool
    }

    return canReachLast(0)
}
```

#### Bottom-Up Solution :arrow_up:

```kotlin
fun canJump(nums: IntArray): Boolean {
    val canReachLastBools =
        BooleanArray(size = nums.size)
        .also {
            // Set last value.
            it[it.lastIndex] = true
        }

    for (i: Int in canReachLastBools.lastIndex - 1 downTo 0) {
        canReachLastBools[i] =
            (i + 1..min(i + nums[i], canReachLastBools.lastIndex))
            .any { canReachLastBools[it] }
    }

    return canReachLastBools.first()
}
```