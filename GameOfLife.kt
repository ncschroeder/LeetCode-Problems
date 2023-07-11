/*
No, this challenge isn't about the Hasbro board game named The Game of Life. This is about a different Game of Life.

https://leetcode.com/problems/game-of-life/

According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
- Any live cell with fewer than two live neighbors dies as if caused by under-population.
- Any live cell with two or three live neighbors lives on to the next generation.
- Any live cell with more than three live neighbors dies, as if by over-population.
- Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid `board`, return the next state.
*/

fun gameOfLife(board: Array<IntArray>): Unit {
    // getNextStateValue checks how many live neighbors the cell at the param row and col has and then checks if that cell is live and then returns either 1 or 0 depending on these
    fun getNextStateValue(row: Int, col: Int): Int =
        arrayOf(
            Pair(row - 1, col - 1),
            Pair(row - 1, col),
            Pair(row - 1, col + 1),
            Pair(row, col - 1),
            Pair(row, col + 1),
            Pair(row + 1, col - 1),
            Pair(row + 1, col),
            Pair(row + 1, col + 1)
        )
        .count { (otherRow, otherCol) -> board.getOrNull(otherRow)?.getOrNull(otherCol) == 1 }
        .let { liveNeighborsCount: Int ->
            if (board[row][col] == 1) {
                if (liveNeighborsCount in 2..3) 1 else 0
            } else if (liveNeighborsCount == 3) 1
            else 0
        }

    val newBoard: List<List<Int>> =
        board.indices.map { row ->
            board.first().indices.map { col -> getNextStateValue(row, col) }
        }

    // The description says to return the next state but that must be a mistake because the return type is Unit (equivalent of void).
    // I mutated the param board and submitted and that got accepted so that must have been what they meant.
    for ((index, row) in newBoard.withIndex()) {
        board[index] = row.toIntArray()
    }
}