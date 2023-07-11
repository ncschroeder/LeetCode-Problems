/*
https://leetcode.com/problems/word-search/

Given an m x n grid of characters `board` and a string `word`, return `true` if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
or vertically neighboring. The same letter cell may not be used more than once.

Example:
Input: board = [
    ["A","B","C","E"],
    ["S","F","C","S"],
    ["A","F","E","E"]
]
word = "ABCCEF"
Output: true
Explanation: Start at A in the 1st row and col, go right 2 cols to get the B and the C, go down 2 rows to get the 
C and the E, go left 1 col to get the F.
*/

fun exist(board: Array<CharArray>, word: String): Boolean {
    // canFormWord returns true if the param word starting at startIndex can be formed when starting at
    // startCoord in the grid and with all previously visited coords in visitedCoords
    fun canFormWord(startIndex: Int, startCoord: Pair<Int, Int>, visitedCoords: Set<Pair<Int, Int>>): Boolean {
        if (startIndex > word.lastIndex) {
            return true
        }

        val (row, col) = startCoord
        val newVisitedCoords: Set<Pair<Int, Int>> = visitedCoords + startCoord
        return arrayOf(
                Pair(row - 1, col),
                Pair(row, col + 1),
                Pair(row + 1, col),
                Pair(row, col - 1)
            )
            .filter { otherCoord ->
                val (otherRow, otherCol) = otherCoord
                board.getOrNull(otherRow)?.getOrNull(otherCol) == word[startIndex] && otherCoord !in visitedCoords
            }
            .any { canFormWord(startIndex = startIndex + 1, startCoord = it, visitedCoords = newVisitedCoords) }
    }

    for (row in board.indices) {
        for (col in board.first().indices) {
            if (board[row][col] == word.first() && canFormWord(startIndex = 1, startCoord = Pair(row, col), visitedCoords = emptySet())) {
                return true
            }
        }
    }

    return false
}