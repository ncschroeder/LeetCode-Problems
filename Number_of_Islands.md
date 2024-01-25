## Number of Islands :desert_island:
### Difficulty: Medium
### [Link](https://leetcode.com/problems/number-of-islands/)

### Description

Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return the number of islands.

An *island* is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

### Example 1

#### Input

```
grid =
    [
        ["1","1","1","1","0"],
        ["1","1","0","1","0"],
        ["1","1","0","0","0"],
        ["0","0","0","0","0"]
    ]
```

#### Output
`1`

### Example 2

#### Input

```
grid =
    [
        ["1","1","0","0","0"],
        ["1","1","0","0","0"],
        ["0","0","1","0","0"],
        ["0","0","0","1","1"]
    ]
```

#### Output
`3`

### Constraints
- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 300`
- `grid[i][j]` is `'0'` or `'1'`.

### Solutions

To solve this, iterate througn all the cells in the grid and when we find a cell with unvisited land, do a depth-first search (DFS) on adjacent cells with unvisited land to explore the whole island.

This problem is similar to an assignment I got in my "Algorithms and Advanced Data Structures" class in fall 2019. We were learning about graph theory and DFS and the assignment was to implement a function that could find the number of friend circles in a graph. This function has an adjacency matrix for friendship statuses as a param. The professor had the assignment be to solve this particular problem since a student of his got asked to solve it in a coding interview. This friends circles problem is virtually identical to the LeetCode problem ["Number of Provinces"](https://leetcode.com/problems/number-of-provinces/).

#### Refactored Solution

Since `Cell` is a `data class`, the hash value of an instance of it is based on the `row` and `col` properties. This allows the `visitedCells` set to work as we want it to. More info about data classes can be found in the Kotlin Docs [here](https://kotlinlang.org/docs/data-classes.html).

```kotlin
fun numIslands(grid: Array<CharArray>): Int {
    data class Cell(val row: Int, val col: Int) {
        fun getAdjacentCells() =
            listOf(
                Cell(row - 1, col),
                Cell(row, col + 1),
                Cell(row + 1, col),
                Cell(row, col - 1)
            )
    }

    val visitedCells = HashSet<Cell>()

    fun isUnvisitedLand(cell: Cell): Boolean =
        grid.getOrNull(cell.row)?.getOrNull(cell.col) == '1' && cell !in visitedCells

    fun exploreIsland(startCell: Cell) {
        // Used as a stack for a DFS.
        val cellsToCheck =
            ArrayDeque<Cell>()
            .apply { add(startCell) }

        while (cellsToCheck.isNotEmpty()) {
            val cell = cellsToCheck.removeLast()
            if (isUnvisitedLand(cell)) {
                visitedCells.add(cell)
                cellsToCheck.addAll(cell.getAdjacentCells())
            }
        }
    }

    var numIslands = 0

    for (row: Int in grid.indices) {
        for (col: Int in grid.first().indices) {
            Cell(row, col).let {
                if (isUnvisitedLand(it)) {
                    numIslands++
                    exploreIsland(it)
                }
            }
        }
    }

    return numIslands
}
```

#### Original Solution

```kotlin
fun numIslands(grid: Array<CharArray>): Int {
    val checkedCoordinates = mutableSetOf<Pair<Int, Int>>()
    val rowLastIndex = grid.lastIndex
    val columnLastIndex = grid[0].lastIndex

    // checkCoordinate returns true if the coordinate provided is part of an island and hasn't been checked yet.
    // Returns false otherwise.
    fun checkCoordinate(coordinate: Pair<Int, Int>): Boolean {
        val row = coordinate.first
        val column = coordinate.second

        if (row in 0..rowLastIndex && column in 0..columnLastIndex && grid[row][column] != '0' && coordinate !in checkedCoordinates) {
            checkedCoordinates.add(coordinate)
            
            for (p in 
                arrayOf(
                    Pair(row - 1, column), // Top
                    Pair(row, column + 1), // Right
                    Pair(row + 1, column), // Bottom
                    Pair(row, column - 1) // Left
                )
            ) {
                checkCoordinate(p)
            }

            return true
        }

        return false
    }

    var islandCount = 0

    for (i in 0..grid.lastIndex) {
        for (j in 0..grid[i].lastIndex) {
            if (checkCoordinate(Pair(i, j))) {
                islandCount++
            }
        }
    }

    return islandCount
}
```