## Group Anagrams
### Difficulty: Medium
### [Link](https://leetcode.com/problems/group-anagrams/)

### Description

Given an array of strings `strs`, group the anagrams together. You can return the answer in any order.

An *anagram* is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

### Example

#### Input
`strs = ["eat","tea","tan","ate","nat","bat"]`

#### Output
`[["bat"],["nat","tan"],["ate","eat","tea"]]`

### Constraints
- <code>1 <= strs.length <= 10<sup>4</sup></code>
- `0 <= strs[i].length <= 100`
- `strs[i]` consists of lowercase English letters.

### Solution

```kotlin
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    /*
    2 words are anagrams of each other if they have the same letters and each letter appears the
    same amount of times.

    Let anagramGroups be a map where the keys will be letterCounts maps created below and the values
    will be lists of strings that have the same letters as the keys in the letterCounts map and each
    letter appears in the string the same amount of times as its value in the letterCounts map.
    */
    val anagramGroups = HashMap<Map<Char, Int>, MutableList<String>>()

    for (s: String in strs) {
        /*
        Let letterCounts be a map where the keys are the letters
        in s and the values are the counts of those letters in s.
        */
        val letterCounts: Map<Char, Int> =
            s
            .groupingBy { it }
            .eachCount()

        val anagrams: MutableList<String>? = anagramGroups[letterCounts]
        
        if (anagrams != null) {
            anagrams.add(s)
        } else {
            anagramGroups[letterCounts] = mutableListOf(s)
        }
    }

    return anagramGroups.values.toList()
}
```