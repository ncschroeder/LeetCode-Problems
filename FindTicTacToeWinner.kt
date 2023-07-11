/*
https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/

Tic-tac-toe is played by two players A and B on a 3 x 3 grid. The rules of Tic-Tac-Toe are:
- Players take turns placing characters into empty squares ' '.
- The first player A always places 'X' characters, while the second player B always places 'O' characters.
- 'X' and 'O' characters are always placed into empty squares, never on filled ones.
- The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
- The game also ends if all squares are non-empty.
- No more moves can be played if the game is over.

Given a 2D integer array `moves` where `moves[i]` = `[row<sub>i</sub>, col<sub>i</sub>]` indicates that the ith move will be played on `grid[row<sub>i</sub>][col<sub>i</sub>]`.
Return the winner of the game if it exists (A or B). In case the game ends in a draw return "Draw". If there are still movements to play return "Pending".

You can assume that `moves` is valid (i.e., it follows the rules of Tic-Tac-Toe), the grid is initially empty, and A will play first.
*/

fun tictactoe(moves: Array<IntArray>): String {
    // Find out how the game went. Let grid be a List of Arrays that contain the row contents. Since the 2 players
    // are "A" and "B" and we have to return 1 of those if there's a winner, have the row contents be either "A", "B", or null.
    val grid: List<Array<String?>> = List(size = 3, init = { arrayOfNulls<String>(size = 3) })
    for ((index, rowAndCol) in moves.withIndex()) {
        val (row, col) = rowAndCol
        grid[row][col] = if (index % 2 == 0) "A" else "B"
    }

    // Create List of Lists where each nested List contains 3 values for the contents of the rows, columns, and diagonal groups.
    // If 1 of the Lists in there has all 3 values that aren't null and are the same, then that value is the winner.
    return buildList<List<String?>>(capacity = 8) {
            // Rows
            addAll(grid.map { it.toList() })

            // Columns
            addAll((0..2).map { col -> (0..2).map { row -> grid[row][col] } })

            // Diagonals
            add((0..2).map { grid[it][it] })
            add((0..2).map { grid[2 - it][it] })
        }
        .firstOrNull {
            (s1, s2, s3): List<String?> ->
                s1 != null && s1 == s2 && s1 == s3
        }
        ?.first()
        ?: if (moves.size == 9) "Draw" else "Pending"
}