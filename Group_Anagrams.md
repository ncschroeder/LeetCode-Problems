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
    2 words are anagrams if they have the same chars and each char appears the same amount of times.

    Let anagramGroups be a map where the keys will be charCounts maps created below and the values will be lists of strings that
    have the same chars as the keys in the charCounts map and each char appears in the string the same amount of times as its value
    in the charCounts map.
    */
    val anagramGroups = HashMap<Map<Char, Int>, MutableList<String>>()

    for (s: String in strs) {
        /*
        Let charCounts be a map where the keys are the chars that are in the string in the current iteration and the
        values are the counts of how many times that char appears in that string.
        */
        val charCounts = HashMap<Char, Int>()
        for (c: Char in s) {
            charCounts[c] = (charCounts[c] ?: 0) + 1
        }

        val anagrams: MutableList<String>? = anagramGroups[charCounts]
        
        if (anagrams != null) {
            anagrams.add(s)
        } else {
            anagramGroups[charCounts] = mutableListOf(s)
        }
    }

    return anagramGroups.values.map { it as List<String> }
}
```