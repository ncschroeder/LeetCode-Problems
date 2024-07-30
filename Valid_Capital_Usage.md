## Valid Capital Usage
### Difficulty: Easy
### [Link](https://leetcode.com/problems/detect-capital/)

### Description

We define the usage of capitals in a word to be right when one of the following cases holds:
- All letters in this word are capitals, like "USA".
- All letters in this word are not capitals, like "leetcode".
- Only the first letter in this word is capital, like "Google".

Given a string `word`, return `true` if the usage of capitals in it is right.

### Constraints
- `1 <= word.length <= 100`
- `word` consists of lowercase and uppercase English letters.

### Regex Solution

```kotlin
val regex = Regex("([A-Z]?[a-z]*)|[A-Z]+")

fun detectCapitalUse(word: String): Boolean =
    regex matches word
```

### Non-Regex Solution

I came up with this before I realized that regex could be used.

```kotlin
fun detectCapitalUse(word: String): Boolean =
    when {
        word.length == 1 -> true

        word.first().isLowerCase() -> word.all { it.isLowerCase() }

        word[1].isUpperCase() -> word.all { it.isUpperCase() }

        else -> word.drop(2).all { it.isLowerCase() }
    }
```