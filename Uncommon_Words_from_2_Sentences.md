## Uncommon Words from 2 Sentences
### Difficulty: Easy
### [Link](https://leetcode.com/problems/uncommon-words-from-two-sentences/)

### Description
A *sentence* is a string of single-space separated words where each word consists only of lowercase letters.

A word is *uncommon* if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Given two sentences `s1` and `s2`, return a list of all the uncommon words. You may return the answer in any order.

### Example 1

#### Input
`s1 = "this apple is sweet", s2 = "this apple is sour"`

#### Output
`["sweet","sour"]`

### Example 2

#### Input
`s1 = "apple apple", s2 = "banana"`

#### Output
`["banana"]`

### Constraints
- `1 <= s1.length, s2.length <= 200`
- `s1` and `s2` consist of lowercase English letters and spaces.
- `s1` and `s2` do not have leading or trailing spaces.
- All the words in `s1` and `s2` are separated by a single space.

### Solution

```kotlin
fun uncommonFromSentences(s1: String, s2: String): Array<String> {
    val (allWords1: Set<String>, wordsThatAppearOnce1: Set<String>) = getWordSets(s1)
    val (allWords2: Set<String>, wordsThatAppearOnce2: Set<String>) = getWordSets(s2)

    return wordsThatAppearOnce1.minus(allWords2)
        .plus(wordsThatAppearOnce2 - allWords1)
        .toTypedArray()
}

fun getWordSets(sentence: String): Pair<Set<String>, Set<String>> {
    val allWords = HashSet<String>()
    val wordsThatAppearOnce = HashSet<String>()

    for (word: String in sentence.split(" ")) {
        if (word !in allWords) {
            allWords.add(word)
            wordsThatAppearOnce.add(word)
        } else if (word in wordsThatAppearOnce) {
            wordsThatAppearOnce.remove(word)
        }
    }

    return Pair(allWords, wordsThatAppearOnce)
}
```