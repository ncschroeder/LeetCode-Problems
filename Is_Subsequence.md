## Is Subsequence
### Difficulty: Easy
### [Link](https://leetcode.com/problems/is-subsequence/)

### Description
Given two strings `s` and `t`, return `true` if `s` is a subsequence of `t`, or `false` otherwise.

A *subsequence* of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.

### Example 1

#### Input
`s = "abc", t = "ahbgdc"`

### Output
`true`

### Explanation
```
"ahbgdc"
 ^-^--^
```

### Example 2

#### Input
`s = "axc", t = "ahbgdc"`

### Output
`false`

### Explanation
There's no 'x' in `t`.

### Constraints
- `0 <= s.length <= 100`
- <code>0 <= t.length <= 10<sup>4</sup></code>
- `s` and `t` consist only of lowercase English letters.

### Solution

```kotlin
fun isSubsequence(s: String, t: String): Boolean {
    // Start tCharIndex at -1 so that the search start index is 0 on the 1st iteration.
    var tCharIndex = -1
    
    for (sChar in s) {
        tCharIndex = t.indexOf(char = sChar, startIndex = tCharIndex + 1)
        if (tCharIndex == -1) return false
    }

    return true
}
```