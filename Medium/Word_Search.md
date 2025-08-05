## Word Search
### Difficulty: Medium
### [Link](https://leetcode.com/problems/word-search/)

### Description

Given an `m x n` grid of characters `board` and a string `word`, return `true` if `word` exists in the grid.

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

To solve this, iterate through the letters on the board. When we find the first letter of the word, do a depth-first search with backtracking :rewind: on adjacent letters to see if we can find the whole word.

#### Refactored Solution with Efficient Backtracking

Since `Cell` is a `data class`, the hash value of an instance of it is based on the `row` and `col` properties. This allows the `cellsInCurPath` set to work as we want it to. More info about data classes can be found in the Kotlin Docs [here](https://kotlinlang.org/docs/data-classes.html).

```kotlin
fun exist(board: Array<CharArray>, word: String): Boolean {
    val rows: IntRange = board.indices
    val cols: IntRange = board.first().indices
    
    data class Cell(val row: Int, val col: Int) {
        val letter: Char
            get() = board[row][col]
        
        fun getAdjacentCells(): List<Cell> =
            arrayOf(
                Cell(row - 1, col),
                Cell(row, col + 1),
                Cell(row + 1, col),
                Cell(row, col - 1)
            )
            .filter { it.row in rows && it.col in cols }
    }
    
    val cellsInCurPath = HashSet<Cell>()
    
    fun canFormWord(cell: Cell, wordIndex: Int): Boolean {
        when {
            cell in cellsInCurPath || cell.letter != word[wordIndex] -> return false
            
            wordIndex == word.lastIndex -> return true
        }
        
        cellsInCurPath.add(cell)
        
        if (
            cell
            .getAdjacentCells()
            .any { canFormWord(cell = it, wordIndex = wordIndex + 1) }
        ) {
            return true
        }
        
        cellsInCurPath.remove(cell)
        return false
    }
    
    for (row: Int in rows) {
        for (col: Int in cols) {
            if (canFormWord(cell = Cell(row, col), wordIndex = 0)) {
                return true
            }
        }
    }
    
    return false
}
```

#### My 1<sup>st</sup> Solution

This solution is bottleneck-inducing :champagne:. The implementation of backtracking is inefficient since it involves creating a bunch of sets to store visited coords.

```kotlin
fun exist(board: Array<CharArray>, word: String): Boolean {
    /*
    canFormWord returns true if the param word starting at startIndex can be formed when
    starting at startCoord in the grid and with all previously visited coords in visitedCoords.
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