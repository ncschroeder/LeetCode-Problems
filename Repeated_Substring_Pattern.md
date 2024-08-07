## Repeated Substring Pattern
### Difficulty: Easy
### [Link](https://leetcode.com/problems/repeated-substring-pattern/)

### Description

Given a string `s`, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.

### Example 1

#### Input
`s = "abcabcabcabc"`

#### Output
`true`

#### Explanation

It is the substring "abc" four times or the substring "abcabc" twice.

### Example 2

#### Input
`s = "aba"`

#### Output
`false`

### Constraints
- <code>1 <= s.length <= 10<sup>4</sup></code>
- `s` consists of lowercase English letters.

### Solutions

#### Simple Solution

```kotlin
fun repeatedSubstringPattern(s: String): Boolean =
    (1..s.length / 2)
    .any { s.length % it == 0 && s.take(it).repeat(s.length / it) == s }
```

#### More Complex but More Performant Solution

```kotlin
fun repeatedSubstringPattern(s: String): Boolean =
    (1..s.length / 2)
    .any { substringLength: Int ->
        s.length % substringLength == 0 &&
        run {
            val substring = s.take(substringLength)
            
            (substringLength until s.length step substringLength)
            .all { s.substring(it until it + substringLength) == substring }
        }
    }
```