/*
https://leetcode.com/problems/number-of-islands/

Given an m x n 2D binary grid `grid` which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:
Input: grid = [
    ["1","1","0","0","0"],
    ["1","1","0","0","0"],
    ["0","0","1","0","0"],
    ["0","0","0","1","1"]
]
Output: 3
*/

// To solve this, iterate througn all the coordinates. When we find an island that hasn't been checked/visited
// yet, do a depth-first search on the surrounding coordinates to find the whole island.
fun numIslands(grid: Array<CharArray>): Int {
    val checkedCoordinates = HashSet<Pair<Int, Int>>()

    // checkCoordinate returns true if the coordinate provided is part of an island and hasn't been checked yet.
    // Returns false otherwise.
    fun checkCoordinate(coordinate: Pair<Int, Int>): Boolean {
        val row = coordinate.first
        val column = coordinate.second
        if (grid.getOrNull(row)?.getOrNull(column) != '1' || coordinate in checkedCoordinates) {
            return false
        }

        checkedCoordinates.add(coordinate)

        arrayOf(
            Pair(row - 1, column),
            Pair(row, column + 1),
            Pair(row + 1, column),
            Pair(row, column - 1)
        )
        .forEach { checkCoordinate(it) }

        return true
    }

    var islandCount = 0
    for (row in grid.indices) {
        for (col in grid.first().indices) {
            if (checkCoordinate(Pair(row, col))) {
                islandCount++
            }
        }
    }

    return islandCount
}