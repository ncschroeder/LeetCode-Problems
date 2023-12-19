## Shortest Palindrome
### Difficulty: "Hard"
### [Link](https://leetcode.com/problems/shortest-palindrome/)

### Description
You are given a string `s`. You can convert `s` to a palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.

### Example 1

#### Input
`s = "aacecaaa"`

#### Output
`aaacecaaa`

### Example 2

#### Input
`s = "abcd"`

#### Output
`dcbabcd`

### Constraints
- <code>0 <= s.length <= 5 * 10<sup>4</sup></code>
- `s` consists of lowercase English letters only.

### Solution
To solve this, find the longest palindrome that can be made by dropping chars from the right. Start by "dropping" 0 chars. When a palindrome is found: get a string of the dropped chars, reverse it, and return the result of prepending that string to the param string. When we drop all chars besides the 1<sup>st</sup> one, the string formed as a result will always be a palindrome.

```kotlin
fun shortestPalindrome(s: String): String =
    if (s.isEmpty()) s
    else {
        (s.lastIndex downTo 0)
        .first { s.isPalindromeTo(it) }
        .let { s.substring(it + 1..s.lastIndex).reversed() + s }
    }

// This extension function returns true if this string is a palindrome from the start to the index provided, inclusive. Returns false otherwise.
fun String.isPalindromeTo(lastIndex: Int): Boolean =
    (0 until (lastIndex + 1) / 2)
    .all { this[it] == this[lastIndex - it] }
```
