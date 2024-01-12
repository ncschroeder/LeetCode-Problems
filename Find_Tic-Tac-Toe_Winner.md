## Find Tic-Tac-Toe Winner :x: :o: :trophy: :1st_place_medal:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/)

### Description

Tic-Tac-Toe is played by two players A and B on a 3 x 3 grid. The rules of Tic-Tac-Toe are:
- Players take turns placing characters into empty squares.
- The first player A always places X characters, while the second player B always places O characters.
- X and O characters are always placed into empty squares, never on filled ones.
- The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
- The game also ends if all squares are non-empty.
- No more moves can be played if the game is over.

Given a 2D integer array `moves` where <code>moves[i] = [row<sub>i</sub>, col<sub>i</sub>]</code> indicates that the `i`<sup>th</sup> move will be played on <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code>. Return the winner of the game if it exists ("A" or "B"). In case the game ends in a draw, return "Draw". If there are still movements to play, return "Pending".

You can assume that `moves` is valid (i.e., it follows the rules of Tic-Tac-Toe), the grid is initially empty, and A will play first.

### Example 1

#### Input
`moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]`

#### Output
`"A"`

#### Explanation

The grid will look like:

```
X
  X
O O X
```

### Example 2

#### Input
`moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]`

#### Output
`"Draw"`

### Explanation:

The grid will look like:

```
X X O
O O X
X O X
```

### Constraints
- `1 <= moves.length <= 9`
- `moves[i].length == 2`
- <code>0 <= row<sub>i</sub>, col<sub>i</sub> <= 2</code>
- There are no repeated elements on `moves`.
- `moves` follow the rules of Tic-Tac-Toe.

### Solution

```kotlin
fun tictactoe(moves: Array<IntArray>): String {
    /*
    Find out how the game went. Let grid be a 2D list that stores where the players took their turns. Since the 2 players are
    A and B and we have to return 1 of those as a string if there's a winner, let the grid contents be either "A", "B", or null.
    */
    val grid: List<MutableList<String?>> =
        List(size = 3, init = { mutableListOf(null, null, null) })

    for ((index: Int, rowAndCol: IntArray) in moves.withIndex()) {
        val (row, col) = rowAndCol
        grid[row][col] = if (index % 2 == 0) "A" else "B"
    }

    // Let groups be a 2D list where each nested list contains 3 values for the contents of the rows, columns, and diagonal groups.
    val groups: List<List<String?>> =
        buildList(capacity = 8) {
            // Rows
            addAll(grid)

            // Columns
            for (col: Int in 0 until 3) {
                add((0 until 3).map { grid[it][col] })
            }

            // Diagonal groups
            add((0 until 3).map { grid[it][it] })
            add((0 until 3).map { grid[2 - it][it] })
        }

    // If a group has values that are all the same and aren't null, then that value is the winner.
    return groups
        .firstOrNull { (s1, s2, s3): List<String?> -> s1 != null && s1 == s2 && s1 == s3 }
        ?.first()
        ?: if (moves.size == 9) "Draw" else "Pending"
}
```