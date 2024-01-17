## Game of Life
### Difficulty: Medium
### [Link](https://leetcode.com/problems/game-of-life/)

No, this problem isn't about [the Hasbro board game named "The Game of Life"](https://en.wikipedia.org/wiki/The_Game_of_Life).

### Description

According to [Wikipedia's article](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life), "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970." 

The board is made up of an `m x n` grid of cells, where each cell has an initial state: live (represented by a `1`) or dead (represented by a `0`).

Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
1. Any live cell with fewer than two live neighbors dies as if caused by under-population.
2. Any live cell with two or three live neighbors lives on to the next generation.
3. Any live cell with more than three live neighbors dies, as if by over-population.
4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the `m x n` grid `board`, return the next state.

### Example 1

#### Input

```
board =
    [
        [0,1,0],
        [0,0,1],
        [1,1,1],
        [0,0,0]
    ]
```

#### Output

```
[
    [0,0,0],
    [1,0,1],
    [0,1,1],
    [0,1,0]
]
```

### Example 2

#### Input

```
board =
    [
        [1,1],
        [1,0]
    ]
```

#### Output

```
[
    [1,1],
    [1,1]
]
```

### Constraints
- `m == board.length`
- `n == board[i].length`
- `1 <= m, n <= 25`
- `board[i][j]` is `0` or `1`.

### Solutions

The description says to return the next state but that must be a mistake because the return type is `Unit`, the equivalent of `void`. I mutated the `board` param and submitted and that got accepted so that must've been what they meant. A possible explanation of why they have you do that is because there's a follow-up question: "Could you solve it in-place?"

#### Refactored Solution

```kotlin
fun gameOfLife(board: Array<IntArray>): Unit {
    val lastRow: Int = board.lastIndex
    val lastCol: Int = board.first().lastIndex
    
    fun getNextStateValueOfCell(row: Int, col: Int): Int {
        val rowsToCheck: IntRange = max(row - 1, 0)..min(row + 1, lastRow)
        val colsToCheck: IntRange = max(col - 1, 0)..min(col + 1, lastCol)
        var numLiveNeighbors = 0
        
        for (r: Int in rowsToCheck) {
            for (c: Int in colsToCheck) {
                if (!(r == row && c == col) && board[r][c] == 1) {
                    numLiveNeighbors++
                }
            }
        }

        return when {
            board[row][col] == 1 -> if (numLiveNeighbors in 2..3) 1 else 0
            
            numLiveNeighbors == 3 -> 1
            
            else -> 0
        }                      
    }

    val newBoard: List<List<Int>> =
        (0..lastRow).map { row: Int ->
            (0..lastCol).map { col: Int -> getNextStateValueOfCell(row, col) }
        }

    for ((index: Int, row: List<Int>) in newBoard.withIndex()) {
        board[index] = row.toIntArray()
    }
}
```

#### Original Solution

```kotlin
fun gameOfLife(board: Array<IntArray>): Unit {
    val newBoard = mutableListOf<List<Int>>()

    for (row in board.indices) {
        val newRow = mutableListOf<Int>()

        for (col in board.first().indices) {
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
            .let {
                if (board[row][col] == 1) {
                    if (it in 2..3) 1 else 0
                } else if (it == 3) 1
                else 0
            }
            .let(newRow::add)
        }

        newBoard.add(newRow)
    }
    
    for ((index, row) in newBoard.withIndex()) {
        board[index] = row.toIntArray()
    }
}
```