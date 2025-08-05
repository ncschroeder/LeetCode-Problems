## Nim Game
### Difficulty: Easy
### [Link](https://leetcode.com/problems/nim-game/)

As mentioned in the README, this is a unique problem since the thinking you have to do to come up with the solution is much more complex than the 5 characters of code needed to implement the solution.

### Description

You are playing the following Nim Game with your friend:
- Initially, there is a heap of stones on the table.
- You and your friend will alternate taking turns, and you go first.
- On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.
- The one who removes the last stone is the winner.

Given `n`, the number of stones in the heap, return `true` if you can win the game assuming both you and your friend play optimally, otherwise return `false`.

### Constraints

- <code>1 <= n <= 2<sup>31</sup> - 1</code>

### Solution

- If `n` is 1, 2, or 3; you can win since you can remove all the stones. :trophy:
- If `n` is 4, you can't win since no matter what amount of stones you remove, you'll leave either 1, 2, or 3 for your friend.
- If `n` is 5, 6, or 7; you can win since you can remove some stones and leave 4.
- If `n` is 8, you can't win since you can remove some stones and leave either 5, 6, or 7.

If `n` is > 4 and isn't a multiple of 4, you can reduce the amount of stones to a multiple of 4 and then when your friend removes some stones, they'll reduce the amount to something other than a multiple of 4. If they reduce it to 1, 2, or 3; you can win by removing the remaining stones. Otherwise, you can keep on reducing the amount to a multiple of 4.

```kotlin
fun canWinNim(n: Int): Boolean =
    n % 4 > 0
```