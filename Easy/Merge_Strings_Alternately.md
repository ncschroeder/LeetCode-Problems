## Merge Strings Alternately
### Difficulty: Easy
### [Link](https://leetcode.com/problems/merge-strings-alternately/)

### Description

You are given two strings `word1` and `word2`. Merge the strings by adding letters in alternating order, starting with `word1`. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

### Example 1

#### Input
`word1 = "abc", word2 = "pqr"`

#### Output
`"apbqcr"`

#### Explanation

The merged string will be merged as so:

```
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
```

### Example 2

#### Input
`word1 = "ab", word2 = "pqrs"`

#### Output
`"apbqrs"`

#### Explanation

Notice that as `word2` is longer, "rs" is appended to the end.

```
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s
```

### Constraints

- `1 <= word1.length, word2.length <= 100`
- `word1` and `word2` consist of lowercase English letters.

### My 1<sup>st</sup> Solution

```kotlin
fun mergeAlternately(word1: String, word2: String): String =
    (word1 zip word2)
    .joinToString(
        transform = { (word1Char, word2Char) -> "$word1Char$word2Char" },
        separator = "",
        postfix = if (word1.length > word2.length) word1.drop(word2.length) else word2.drop(word1.length)
    )
```

### My 2<sup>nd</sup> Solution

Some info about the [`buildString`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/build-string.html) function can be found in the README in the "Languages Used" -> "Kotlin" -> "Functions with Receivers" section.

```kotlin
fun mergeAlternately(word1: String, word2: String): String =
    buildString(capacity = word1.length + word2.length) {
        val iterator1: Iterator<Char> = word1.iterator()
        val iterator2: Iterator<Char> = word2.iterator()
        
        while (iterator1.hasNext() && iterator2.hasNext()) {
            append(iterator1.next())
            append(iterator2.next())
        }
        
        (if (iterator1.hasNext()) iterator1 else iterator2)
        .forEach { char -> append(char) }
        
        /*
        Yes, in Kotlin you can iterate through the remaining
        elements of an iterator using a for loop or forEach.
        */
    }
```