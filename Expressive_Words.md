## Expressive Words
### Difficulty: Medium
### [Link](https://leetcode.com/problems/expressive-words/)

### Description

Sometimes people repeat letters to represent extra feeling. For example:
- "hello" -> "heeellooo"
- "hi" -> "hiiii"

In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".

You are given a string `s` and an array of query strings `words`. A query word is *stretchy* if it can be made to be equal to `s` by any number of applications of the following extension operation: choose a group consisting of characters `c`, and add some number of characters `c` to the group so that the size of the group is three or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a size less than three. Also, we could do another extension like "ll" -> "lllll" to get "helllllooo". If `s` = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = `s`.

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


#### String Compression Solution

There's a [LeetCode problem called "String Compression"](https://leetcode.com/problems/string-compression/) and to solve it, you iterate through a char array and find the number of times that each char appears in a row. This solution does that for the param strings and stores the results in lists of `CharGroup`s.

```kotlin
class Solution {
    class CharGroup(val char: Char, val numInARow: Int)

    fun expressiveWords(s: String, words: Array<String>): Int {
        val sCharGroups: List<CharGroup> = s.getCharGroups()

        return words.count {
            val wordCharGroups: List<CharGroup> = it.getCharGroups()

            sCharGroups.size == wordCharGroups.size &&
            (sCharGroups zip wordCharGroups)
            .all { (sCharGroup, wordCharGroup) ->                    
                sCharGroup.char == wordCharGroup.char &&
                sCharGroup.numInARow >= wordCharGroup.numInARow &&
                (sCharGroup.numInARow >= 3 || sCharGroup.numInARow == wordCharGroup.numInARow)
            }
        }
    }

    fun String.getCharGroups(): List<CharGroup> {
        val groups = ArrayList<CharGroup>()
        // This is the char we're keeping track of.
        var trackedChar = this.first()
        var numTrackedCharInARow = 0

        fun addGroup() {
            groups.add(CharGroup(trackedChar, numTrackedCharInARow))
        }

        for (char in this) {
            if (char == trackedChar) {
                numTrackedCharInARow++
            } else {
                addGroup()
                trackedChar = char
                numTrackedCharInARow = 1
            }
        }

        addGroup()
        return groups
    }
}
```

#### Refactored 2 Pointers Solution

```kotlin
class Solution {
    fun expressiveWords(s: String, words: Array<String>): Int =
        words.count { isStretchy(it, s) }

    fun isStretchy(word: String, s: String): Boolean {
        var wordIndex = 0
        var sIndex = 0

        while (true) {
            val char = word[wordIndex]
            if (s[sIndex] != char) return false

            var wordNumCharInARow = 1
            while (word.getOrNull(++wordIndex) == char) {
                ++wordNumCharInARow
            }
            
            var sNumCharInARow = 1
            while (s.getOrNull(++sIndex) == char) {
                ++sNumCharInARow
            }
            
            when {
                wordNumCharInARow > sNumCharInARow -> return false

                sNumCharInARow < 3 && sNumCharInARow != wordNumCharInARow -> return false

                wordIndex > word.lastIndex -> return sIndex > s.lastIndex

                sIndex > s.lastIndex -> return false
            }
        }
    }
}
```

#### My 1<sup>st</sup> Solution (2 Pointers)

```kotlin
class Solution {
    fun expressiveWords(s: String, words: Array<String>): Int =
        words.count { isStretchy(it, s) }
        
    fun isStretchy(word: String, s: String): Boolean {
        var wi1 = 0
        var wi2: Int?
        var si1 = 0
        var si2: Int?
        
        while (true) {
            if (word[wi1] != s[si1]) {
                return false
            }

            si2 = (si1 + 1..s.lastIndex).firstOrNull { s[it] != s[si1] }
            wi2 = (wi1 + 1..word.lastIndex).firstOrNull { word[it] != word[wi1] }

            val wordDiff = (wi2 ?: word.length) - wi1
            val sDiff = (si2 ?: s.length) - si1
            if (wordDiff > sDiff) {
                return false
            }

            if (sDiff < 3 && wordDiff != sDiff) {
                return false
            }

            if (si2 == null) {
                return wi2 == null
            } else if (wi2 == null) {
                return false
            }

            si1 = si2
            wi1 = wi2
        }
    }
}
```
