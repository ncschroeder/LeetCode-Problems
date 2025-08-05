## Spiral Matrix
### Difficulty: Medium
### [Link](https://leetcode.com/problems/spiral-matrix/)

### Description

Given an `m x n` matrix, return all elements of the matrix in spiral order.

### Example 1

#### Input

```
matrix =
    [
        [1,2,3],
        [4,5,6],
        [7,8,9]
    ]
```

#### Output
`[1,2,3,6,9,8,7,4,5]`

#### Explanation

```
1 -> 2 -> 3
          |
          V
4 -> 5    6
^         |
|         V
7 <- 8 <- 9
```

### Example 2

#### Input

```
matrix =
    [
        [1,2, 3, 4],
        [5,6, 7, 8],
        [9,10,11,12]
    ]
```

#### Output
`[1,2,3,4,8,12,11,10,9,5,6,7]`

### Constraints

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 10`
- `-100 <= matrix[i][j] <= 100`

#### My Favorite Solution

I'm going to use the term "level" for groups of ints in a matrix. Level 0 contains the ints that are on the outer edge of the matrix. Level 1 contains the ints in the matrix that would be on the outer edge if the outer edge, or level 0, was removed. Level 2 contains the ints in the matrix that would be on the outer edge if level 0 and level 1 were removed, and so on.

```kotlin
fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    val matrixLastRow: Int = matrix.lastIndex
    val matrixLastCol: Int = matrix.first().lastIndex
    val spiralInts = ArrayList<Int>(matrix.size * matrix.first().size)
    var level = 0
    
    while (true) {
        /*
        Add the ints in the current level to spiralInts. Add the ints on the top, then right, then
        bottom, then left. The top and bottom will include the ints on the corners and the right
        and left won't. Break the loop when all ints in the matrix have been added to sprialInts.
        */
        
        val levelLastRow: Int = matrixLastRow - level
        val levelLastCol: Int = matrixLastCol - level
        
        for (col: Int in level..levelLastCol) {
            spiralInts.add(matrix[level][col])
        }
        
        // Check if there's 1 row in the current level.
        if (level == levelLastRow) break
        
        val levelPlus1: Int = level + 1
        
        for (row: Int in levelPlus1 until levelLastRow) {
            spiralInts.add(matrix[row][levelLastCol])
        }
        
        for (col: Int in levelLastCol downTo level) {
            spiralInts.add(matrix[levelLastRow][col])
        }
        
        // Check if there's 1 column in the current level.
        if (level == levelLastCol) break
        
        for (row: Int in levelLastRow - 1 downTo levelPlus1) {
            spiralInts.add(matrix[row][level])
        }
        
        /*
        Check if the rows in the current level are next to each other
        or the columns in the current level are next to each other.
        */
        if (levelPlus1 == levelLastRow || levelPlus1 == levelLastCol) break
        
        level++
    }
    
    return spiralInts
}
```

#### My 1<sup>st</sup> Solution

Info about tail recursive functions can be found in the Kotlin Docs [here](https://kotlinlang.org/docs/functions.html#tail-recursive-functions).

```kotlin
fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    tailrec fun spiralOrderRecursive(matrix: List<List<Int>>, ints: MutableList<Int>): List<Int> {
        if (matrix.isEmpty() || matrix.first().isEmpty()) {
            return ints
        }
        
        ints.addAll(matrix.first())
        if (matrix.size == 1) {
            return ints
        }
        
        val lastCol = matrix.first().lastIndex
        for (row in 1 until matrix.lastIndex) {
            ints.add(matrix[row][lastCol])
        }
        
        ints.addAll(matrix.last().asReversed())
        if (lastCol == 0) {
            return ints
        }
        
        for (row in matrix.lastIndex - 1 downTo 1) {
            ints.add(matrix[row][0])
        }
        
        val subMatrix =
            (1 until matrix.lastIndex)
            .map { matrix[it].slice(1 until matrix.first().lastIndex) }
        
        return spiralOrderRecursive(subMatrix, ints)
    }
    
    return spiralOrderRecursive(matrix.map { it.asList() }, ArrayList(matrix.size * matrix.first().size))
}
```