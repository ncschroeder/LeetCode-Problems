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

### Solution

```kotlin
fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    val spiralInts = ArrayList<Int>(matrix.size * matrix.first().size)

    // Let matrixLeft be a deque of deques that starts off with the same contents as the matrix param.
    val matrixLeft: ArrayDeque<ArrayDeque<Int>> =
        matrix.mapTo(ArrayDeque(initialCapacity = matrix.size)) {
            row: IntArray -> ArrayDeque(elements = row.asList())
        }
    
    while (true) {
        /*
        Add ints on the top edge, then right edge, then bottom edge, then left edge. The top and bottom edges will include the ints
        on the corners and the right and left edges won't. Break the loop when all the ints in the matrix have been added to spiralInts.
        */
        
        spiralInts.addAll(matrixLeft.first())
        
        val numRows: Int = matrixLeft.size
        if (numRows == 1) break
        val numCols: Int = matrixLeft.first().size
        val lastCol: Int = numCols - 1

        for (row: Int in 1 until matrixLeft.lastIndex) {
            spiralInts.add(matrixLeft[row][lastCol])
        }

        spiralInts.addAll(matrixLeft.last().asReversed())
        if (numCols == 1) break

        for (row: Int in matrixLeft.lastIndex - 1 downTo 1) {
            spiralInts.add(matrixLeft[row][0])
        }

        if (numRows == 2 || numCols == 2) break

        // Remove ints on the edge.
        matrixLeft.removeLast()
        matrixLeft.removeFirst()

        for (row: ArrayDeque<Int> in matrixLeft) {
            row.removeLast()
            row.removeFirst()
        }
    }

    return spiralInts
}
```