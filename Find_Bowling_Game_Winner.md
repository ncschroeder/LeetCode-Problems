## Find Bowling Game Winner :bowling: :trophy: :1st_place_medal:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/determine-the-winner-of-a-bowling-game/)

### Description

You are given two 0-indexed integer arrays `player1` and `player2`, that represent the number of pins that player 1 and player 2 hit in a bowling game, respectively. The bowling game consists of `n` turns, and the number of pins in each turn is exactly 10.

Assume a player hit <code>x<sub>i</sub></code> pins in the `i`<sup>th</sup> turn. The value of the `i`<sup>th</sup> turn for the player is:
- <code>2x<sub>i</sub></code> if the player hit 10 pins in any of the previous two turns.
- Otherwise, it is <code>x<sub>i</sub></code>.

The score of the player is the sum of the values of their `n` turns.

Return:
- `1` if the score of player 1 is more than the score of player 2.
- `2` if the score of player 2 is more than the score of player 1.
- `0` in case of a draw.

### Example 1

#### Input
`player1 = [4,10,7,9], player2 = [6,5,2,3]`

#### Output
`1`

#### Explanation

The score of `player1` is 4 + 10 + 2\*7 + 2\*9 = 46 and the score of `player2` is 6 + 5 + 2 + 3 = 16.

### Example 2

#### Input
`player1 = [2,3], player2 = [4,1]`

#### Output
`0`

#### Explanation

The score of `player1` is 2 + 3 = 5 and the score of `player2` is 4 + 1 = 5.

### Constraints
- `n == player1.length == player2.length`
- `1 <= n <= 1000`
- `0 <= player1[i], player2[i] <= 10`

### Solution

```kotlin
fun isWinner(player1: IntArray, player2: IntArray): Int {
    val player1Score: Int = getScore(player1)
    val player2Score: Int = getScore(player2)
    
    return when {
        player1Score > player2Score -> 1
        
        player1Score < player2Score -> 2
        
        else -> 0
    }
}

fun getScore(player: IntArray): Int =
    player
    .withIndex()
    .sumOf { (index: Int, numPins: Int) ->
        val doubleScore: Boolean =
            (1..2).any { player.getOrNull(index - it) == 10 }
        
        if (doubleScore) numPins * 2 else numPins
    }
```