## First Completely Painted Row or Column :art: :paintbrush:
### Difficulty: Medium
### [Link](https://leetcode.com/problems/first-completely-painted-row-or-column/)

### Description
You are given a 0-indexed integer array `arr`, and an `m x n` integer matrix `mat`. `arr` and `mat` both contain all the integers in the range [1, `m * n`].

Go through each index `i` in `arr` starting from index 0 and paint the cell in `mat` containing the integer `arr[i]`.

Return the smallest index `i` at which either a row or a column will be completely painted in `mat`.

### Example 1

#### Input
```
arr = [1,3,4,2], 
mat =
    [
        [1,4],
        [2,3]
    ]
```

#### Output
`2`

#### Explanation
Both the first row and second column of the matrix become fully painted at `arr[2]`.

### Example 2

#### Input
```
arr = [2,8,7,4,1,3,5,6,9],
mat =
    [
        [3,2,5],
        [1,4,6],
        [8,7,9]
    ]
```

#### Output
`3`

#### Explanation
The second column becomes fully painted at `arr[3]`.

### Constraints
- `m == mat.length`
- `n = mat[i].length`
- `arr.length == m * n`
- <code>1 <= m, n <= 10<sup>5</sup></code>
- <code>1 <= m * n <= 10<sup>5</sup></code>
- `1 <= arr[i], mat[r][c] <= m * n`
- All the integers of `arr` are unique.
- All the integers of `mat` are unique.

### Refactored Efficient Solution
This has a time complexity of $O(m \times n)$.

```kotlin
fun firstCompleteIndex(arr: IntArray, mat: Array<IntArray>): Int {
    val numRows: Int = mat.size
    val numCols: Int = mat.first().size
    
    val matrixLocations = HashMap<Int, Pair<Int, Int>>(numRows * numCols)
    for (row: Int in 0 until numRows) {
        for (col: Int in 0 until numCols) {
            matrixLocations[mat[row][col]] = Pair(row, col)
        }
    }
    
    /*
    rowPaintedCellCounts and colPaintedCellCounts are int arrays where the value at an index will be the count of painted
    cells in the row or column with that index. For example, rowPaintedCellCounts[0] will be the painted cell count of the
    1st row and colPaintedCellCounts[1] will be the painted cell count of the 2nd column.
    */
    val rowPaintedCellCounts = IntArray(size = numRows)
    val colPaintedCellCounts = IntArray(size = numCols)

    return arr
        .indices
        .first {
            val (row: Int, col: Int) = matrixLocations.getValue(arr[it])
            ++rowPaintedCellCounts[row] == numCols || ++colPaintedCellCounts[col] == numRows
        }
}
```

### Original Bottleneck-Inducing Solution :champagne:
This has a time complexity of $O((m \times n)(m + n))$.

```kotlin
fun firstCompleteIndex(arr: IntArray, mat: Array<IntArray>): Int {
    val boolMatrix: List<BooleanArray> =
        List(size = mat.size, init = { BooleanArray(size = mat.first().size) })

    val matLocations = HashMap<Int, Pair<Int, Int>>(mat.size * mat.first().size)
    for (row in mat.indices) {
        for (col in mat.first().indices) {
            matLocations[mat[row][col]] = Pair(row, col)
        }
    }

    for ((index, int) in arr.withIndex()) {
        val (row, col) = matLocations.getValue(int)
        boolMatrix[row][col] = true

        if (boolMatrix[row].all { it } || boolMatrix.indices.all { boolMatrix[it][col] }) {
            return index
        }
    }

    throw Exception("No answer found")
}
```