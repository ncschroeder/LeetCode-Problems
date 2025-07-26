## Shortest Palindrome
### Difficulty: Hard
### [Link](https://leetcode.com/problems/shortest-palindrome/)

LeetCode designated this problem as hard but I don't recall having much difficulty in solving it. As of July 2025, this is 1 of 2 hard problems I've been able to solve and there are plenty of medium problems and even some easy ones that I haven't been able to solve. My LeetCode profile says that I solved 3 hard problems but for 1 of them, I cheated to see if LeetCode would be able to catch me and it didn't.

### Description

You are given a string `s`. You can convert `s` to a palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.

### Example 1

#### Input
`s = "aacecaaa"`

#### Output
`"aaacecaaa"`

### Example 2

#### Input
`s = "abcd"`

#### Output
`"dcbabcd"`

### Constraints
- <code>0 <= s.length <= 5 * 10<sup>4</sup></code>
- `s` consists of lowercase English letters only.

### Solution

To solve this, find the longest palindrome that can be made by dropping letters from the right. Start by "dropping" 0 letters. When a palindrome is found: get a string of the dropped letters, reverse it, and return the result of prepending that string to the param string. When we drop all letters besides the 1<sup>st</sup> one, the substring to search will always be a palindrome. That's why the usage of the `first` function won't throw an exception.

Info about extension functions can be found in the Kotlin Docs [here](https://kotlinlang.org/docs/extensions.html#extension-functions).

```kotlin
class Solution {
    fun shortestPalindrome(s: String): String =
        if (s.isEmpty()) s
        else {
            (s.lastIndex downTo 0)
            .first { s isPalindromeTo it }
            .let { s.substring(it + 1..s.lastIndex).reversed() + s }
        }

    /*
    This extension function returns true if this string is a palindrome from
    the start to the index provided, inclusive. Returns false otherwise.
    */
    infix fun String.isPalindromeTo(lastIndex: Int): Boolean =
        (0 until (lastIndex + 1) / 2)
        .all { this[it] == this[lastIndex - it] }
}
```