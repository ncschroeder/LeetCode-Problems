## Word Search
### Difficulty: Medium
### [Link](https://leetcode.com/problems/word-search/)

### Description
Given an `m x n` grid of characters `board` and a string `word`, return `true` if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

### Example 1

#### Input
```
board =
    [
        ["A","B","C","E"],
        ["S","F","C","S"],
        ["A","D","E","E"]
    ],
word = "ABCCED"
```

#### Output
`true`

#### Explanation
```
A -> B -> C
          |
          V
          C
          |
          V
     D <- E
```

### Example 2

### Input
```
board =
    [
        ["A","B","C","E"],
        ["S","F","C","S"],
        ["A","D","E","E"]
    ],
word = "ABCB"
```

### Output
`false`

### Constraints
- `m == board.length`
- `n = board[i].length`
- `1 <= m, n <= 6`
- `1 <= word.length <= 15`
- `board` and `word` consists of only lowercase and uppercase English letters.

### Solutions
To solve this, iterate through the letters on the board. When we find the first letter of the word, do a depth-first search with backtracking on adjacent letters to see if we can find the whole word.

#### Refactored Solution with Efficient Backtracking

```kotlin
fun exist(board: Array<CharArray>, word: String): Boolean {
    data class Cell(val row: Int, val col: Int) {
        // If this cell is a valid cell on the board, letterOrNull is the letter at this cell. Otherwise, letterOrNull is null.
        val letterOrNull: Char?
            get() = board.getOrNull(row)?.getOrNull(col)
            
        fun getAdjacentCells() =
            listOf(
                Cell(row - 1, col),
                Cell(row, col + 1),
                Cell(row + 1, col),
                Cell(row, col - 1)
            )
    }
    
    val visitedCells = HashSet<Cell>()
    
    fun canFormWord(cell: Cell, wordIndex: Int): Boolean {
        if (cell.letterOrNull != word[wordIndex] || cell in visitedCells) {
            return false
        }
        
        if (wordIndex == word.lastIndex) return true
        
        visitedCells.add(cell)
        
        cell
        .getAdjacentCells()
        .any { canFormWord(cell = it, wordIndex = wordIndex + 1) }
        .let { if (it) return true }
        
        visitedCells.remove(cell)
        return false
    }

    for (row: Int in board.indices) {
        for (col: Int in board.first().indices) {
            if (canFormWord(cell = Cell(row, col), wordIndex = 0)) {
                return true
            }
        }
    }
    
    return false
}
```

#### Original Bottleneck-Inducing :champagne: Solution with Inefficient Backtracking

```kotlin
fun exist(board: Array<CharArray>, word: String): Boolean {
    /*
    canFormWord returns true if the param word starting at startIndex can be formed when starting at startCoord in
    the grid and with all previously visited coords in visitedCoords.
    */
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
```