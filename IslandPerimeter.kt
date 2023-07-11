/*
https://leetcode.com/problems/island-perimeter/

You are given `row` x `col` `grid` representing a map where `grid[i][j]` = 1 represents land and `grid[i][j]` = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:
Input: grid = [
    [0,1,0,0],
    [1,1,1,0],
    [0,1,0,0],
    [1,1,0,0]
]
Output: 16
*/

// Brute force solution. I call it that since it has to iterate through all rows and cols, unlike the 2nd solution.
fun islandPerimeter1(grid: Array<IntArray>): Int {
    var perimeter = 0
    for (row in grid.indices) {
        for (col in grid.first().indices) {
            if (grid[row][col] == 0) {
                continue
            }

            // Find how many neighboring cells are water and then increment perimeter for each one
            arrayOf(
                Pair(row - 1, col),
                Pair(row, col + 1),
                Pair(row + 1, col),
                Pair(row, col - 1)
            )
            .count { (otherRow, otherCol) -> grid.getOrNull(otherRow)?.getOrNull(otherCol) != 1 }
            .let { perimeter += it }
        }
    }

    return perimeter
}


/*
Solution that uses depth-first search. This gets worse performance than the above solution when ran against all
the test cases that LeetCode decided to use, but this seems more performant for some situations. For this solution,
we just have to find 1 bit of land and then we can use that to find the rest of the island and we're done, since
we're guaranteed that there's only 1 island.
*/
fun islandPerimeter2(grid: Array<IntArray>): Int {
    val visitedCoords = HashSet<Pair<Int, Int>>()
    var perimeter = 0

    fun visit(coord: Pair<Int, Int>) {
        visitedCoords.add(coord)
        val row = coord.first
        val col = coord.second

        // Check neighboring cells. If a cell is water, increment perimeter. Otherwise, visit that cell if it hasn't been visited.
        arrayOf(
            Pair(row - 1, col),
            Pair(row, col + 1),
            Pair(row + 1, col),
            Pair(row, col - 1)
        )
        .forEach { otherRowAndCol ->
            val (otherRow, otherCol) = otherRowAndCol
            if (grid.getOrNull(otherRow)?.getOrNull(otherCol) != 1) {
                perimeter++
            } else if (otherRowAndCol !in visitedCoords) {
                visit(otherRowAndCol)
            }
        }
    }

    outer@ for (row in grid.indices) {
        for (col in grid.first().indices) {
            if (grid[row][col] == 1) {
                visit(Pair(row, col))
                break@outer
            }
        }
    }

    return perimeter
}