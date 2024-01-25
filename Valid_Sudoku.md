## Valid Sudoku
### Difficulty: Medium
### [Link](https://leetcode.com/problems/valid-sudoku/)

### Description

Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
- Each row must contain the digits 1-9 without repetition.
- Each column must contain the digits 1-9 without repetition.
- Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

Note:
- A Sudoku board (partially filled) could be valid but is not necessarily solvable.
- Only the filled cells need to be validated according to the mentioned rules.

### Example 1

#### Input

```
board = 
    [
        ["5","3",".",".","7",".",".",".","."],
        ["6",".",".","1","9","5",".",".","."],
        [".","9","8",".",".",".",".","6","."],
        ["8",".",".",".","6",".",".",".","3"],
        ["4",".",".","8",".","3",".",".","1"],
        ["7",".",".",".","2",".",".",".","6"],
        [".","6",".",".",".",".","2","8","."],
        [".",".",".","4","1","9",".",".","5"],
        [".",".",".",".","8",".",".","7","9"]
    ]
```

#### Output
`true`

### Example 2

In example 1, if we were to replace the 5 in the top left corner with an 8, the output would be `false` since there would be two 8's in the top left 3 x 3 sub-box.

### Constraints
- `board.length == 9`
- `board[i].length == 9`
- `board[i][j]` is a digit `1-9` or `'.'`.

### Solution

```kotlin
fun isValidSudoku(board: Array<CharArray>): Boolean {
    // isInvalid is used for checking if the contents of either a row, column, or box are invalid.
    val isInvalid: (Iterable<Char>) -> Boolean =
        { contents ->
            val digits = HashSet<Char>(9)
            contents.any { it != '.' && !digits.add(it) }
        }
        
    if (board.any { row: CharArray -> isInvalid(row.asIterable()) }) {
        return false
    }
    
    for (col: Int in 0 until 9) {
        val colContents: List<Char> = (0 until 9).map { board[it][col] }
        if (isInvalid(colContents)) return false
    }
    
    val boxStartIndices = listOf(0, 3, 6)
    
    for (startRow: Int in boxStartIndices) {
        for (startCol: Int in boxStartIndices) {
            val boxContents: List<Int> =
                (startRow until startRow + 3)
                .flatMap { row: Int -> board[row].slice(startCol until startCol + 3) }

            if (isInvalid(boxContents)) return false
        }
    }
    
    return true
}
```