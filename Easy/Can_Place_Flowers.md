## Can Place Flowers :rose: :tulip: :blossom: :sunflower:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/can-place-flowers/)

### Description

You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array `flowerbed` containing `0`'s and `1`'s, where `0` means empty and `1` means not empty, and an integer `n`, return `true` if `n` new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and `false` otherwise.

### Examples

Shown below are `flowerbed` examples and the arrows show where flowers can be planted.

```
[1,0,0,0,1]
     ^

[0,0,0]
 ^   ^

[0,0,1,0,0,1,0,0]
 ^             ^

1 of the plots below can have a flower planted in it.
[0,0,0,1]
 ^ ^
```

### Constraints

- <code>1 <= flowerbed.length <= 2 * 10<sup>4</sup></code>
- `flowerbed[i]` is `0` or `1`.
- There are no two adjacent flowers in `flowerbed`.
- `0 <= n <= flowerbed.length`

### Solution

```kotlin
fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
    val indexOfFirst1: Int = flowerbed.indexOf(1)
    var numFlowersPlaceable: Int
    
    if (indexOfFirst1 == -1) {
        /*
        If this condition is true then there are only 0's in the flowerbed. We can place a
        flower at the first plot and after that, we can place 1 flower for every 2 plots.
        */
        numFlowersPlaceable = 1 + ((flowerbed.size - 1) / 2)
        return numFlowersPlaceable >= n
    }
    
    /*
    For the 0's at the start and end of the flowerbed, we can place 1 flower for every 2 plots.
    The index of the first 1 is the same as the number of 0's that the flowerbed starts with.
    */
    numFlowersPlaceable = indexOfFirst1 / 2
    val indexOfLast1: Int = flowerbed.lastIndexOf(1)
    numFlowersPlaceable += (flowerbed.lastIndex - indexOfLast1) / 2
    
    /*
    If the indices of the first and last 1's are different, find groups of 0's and their lengths
    in between the first and last 1's. For these groups, we can't place a flower at the first plot
    but after that, we can place 1 flower for every 2 plots. Have the int range include indexOfLast1
    so the else block runs on the last iteration.
    */
    var num0sInARow = 0
    
    for (i: Int in indexOfFirst1 + 1..indexOfLast1) {
        if (flowerbed[i] == 0) {
            num0sInARow++
        } else {
            numFlowersPlaceable += (num0sInARow - 1) / 2
            num0sInARow = 0
        }
    }
    
    return numFlowersPlaceable >= n
}
```