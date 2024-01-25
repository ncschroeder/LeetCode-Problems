## Expressive Words
### Difficulty: Medium
### [Link](https://leetcode.com/problems/expressive-words/)

### Description

Sometimes people repeat letters to represent extra feeling. For example:
- "hello" -> "heeellooo"
- "hi" -> "hiiii"

In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".

You are given a string `s` and an array of query strings `words`. A query word is *stretchy* if it can be made to be equal to `s` by any number of applications of the following extension operation: choose a group consisting of characters `c`, and add some number of characters `c` to the group so that the size of the group is three or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a size less than three. Also, we could do another extension like "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = `s`.

Return the number of query strings that are stretchy.

### Example 1

#### Input
`s = "heeellooo", words = ["hello", "hi", "helo"]`

#### Output
`1`

#### Explanation

We can extend "e" and "o" in the word "hello" to get "heeellooo".

We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.

### Example 2

#### Input
`s = "zzzzzyyyyy", words = ["zzyy","zy","zyy"]`

#### Output
`3`

### Constraints
- `1 <= s.length, words.length <= 100`
- `1 <= words[i].length <= 100`
- `s` and `words[i]` consist of lowercase letters.

### Solutions

I'll use the term "letter group" to refer to groups of the same letter in a row. Each solution checks the letter groups of a query word and `s` to see if that word is stretchy. The start index params and variables refer to the start indices of the letter groups.

#### Tail Recursive Solution

Info about tail recursive functions can be found in the Kotlin Docs [here](https://kotlinlang.org/docs/functions.html#tail-recursive-functions).

```kotlin
fun expressiveWords(s: String, words: Array<String>): Int =
    words.count { isStretchy(it, s) }

fun isStretchy(word: String, s: String): Boolean {
    tailrec fun checkLetterGroups(wordStartIndex: Int, sStartIndex: Int): Boolean {
        val letter: Char = word[wordStartIndex]
        if (s[sStartIndex] != letter) return false
        
        val wordNextStartIndex: Int? =
            (wordStartIndex + 1..word.lastIndex)
            .firstOrNull { word[it] != letter }

        val sNextStartIndex: Int? =
            (sStartIndex + 1..s.lastIndex)
            .firstOrNull { s[it] != letter }
                
        val wordLetterGroupSize: Int = (wordNextStartIndex ?: word.length) - wordStartIndex
        val sLetterGroupSize: Int = (sNextStartIndex ?: s.length) - sStartIndex

        return when {
            wordLetterGroupSize > sLetterGroupSize -> false

            sLetterGroupSize < 3 && sLetterGroupSize != wordLetterGroupSize -> false

            wordNextStartIndex == null -> sNextStartIndex == null

            else -> sNextStartIndex != null && checkLetterGroups(wordNextStartIndex, sNextStartIndex)
        }
    }

    return checkLetterGroups(0, 0)
}
```

#### Iterative Solution

```kotlin
fun expressiveWords(s: String, words: Array<String>): Int =
    words.count { isStretchy(it, s) }

fun isStretchy(word: String, s: String): Boolean {
    var wordStartIndex = 0
    var sStartIndex = 0

    while (true) {
        val letter: Char = word[wordStartIndex]
        if (s[sStartIndex] != letter) return false
        
        val wordNextStartIndex: Int? =
            (wordStartIndex + 1..word.lastIndex)
            .firstOrNull { word[it] != letter }

        val sNextStartIndex: Int? =
            (sStartIndex + 1..s.lastIndex)
            .firstOrNull { s[it] != letter }
        
        val wordLetterGroupSize: Int = (wordNextStartIndex ?: word.length) - wordStartIndex
        val sLetterGroupSize: Int = (sNextStartIndex ?: s.length) - sStartIndex

        when {
            wordLetterGroupSize > sLetterGroupSize -> return false

            sLetterGroupSize < 3 && sLetterGroupSize != wordLetterGroupSize -> return false

            wordNextStartIndex == null -> return sNextStartIndex == null

            sNextStartIndex == null -> return false

            else -> {
                wordStartIndex = wordNextStartIndex
                sStartIndex = sNextStartIndex
            }
        }
    }
}
```
