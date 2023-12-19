## Convert 1D Array to 2D Array
### Difficulty: Easy
### [Link](https://leetcode.com/problems/convert-1d-array-into-2d-array/)

### Description
You are given a 0-indexed 1-dimensional (1D) integer array `original`, and two integers, `m` and `n`. You are tasked with creating a 2-dimensional (2D) array with `m` rows and `n` columns using all the elements from `original`.

The elements from indices `0` to `n - 1` (inclusive) of `original` should form the first row of the constructed 2D array, the elements from indices `n` to `2 * n - 1` (inclusive) should form the second row of the constructed 2D array, and so on.

Return an `m x n` 2D array constructed according to the above procedure, or an empty 2D array if it is impossible.

### Example 1

#### Input
`original = [1,2,3,4], m = 2, n = 2`

#### Output
`[[1,2],[3,4]]`

### Example 2

#### Input
`original = [1,2], m = 1, n = 1`

#### Output
`[]`

#### Explanation
There are 2 elements in `original`. It is impossible to fit 2 elements in a 1 x 1 2D array, so return an empty 2D array.

### Constraints
- <code>1 <= original.length <= 5 * 10<sup>4</sup></code>
- <code>1 <= original[i] <= 10<sup>5</sup></code>
- <code>1 <= m, n <= 4 * 10<sup>4</sup></code>

### Solution

```kotlin
fun construct2DArray(original: IntArray, m: Int, n: Int): Array<IntArray> =
    if (original.size != m * n) emptyArray()
    else {
        (0 until original.size step n)
        .map { original.sliceArray(it until it + n) }
        .toTypedArray()
    }
```