## Word Break
### Difficulty: Medium
### [Link](https://leetcode.com/problems/word-break/)

### Description

Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

### Example 1

#### Input
`s = "leetcode", wordDict = ["leet","code"]`

#### Output
`true`

### Example 2

#### Input
`s = "applepenapple", wordDict = ["apple","pen"]`

#### Output
`true`

### Example 3

#### Input
`s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]`

#### Output
`false`

### Constraints

- `1 <= s.length <= 300`
- `1 <= wordDict.length <= 1000`
- `1 <= wordDict[i].length <= 20`
- `s` and `wordDict[i]` consist of only lowercase English letters.
- All the strings of `wordDict` are unique.

### Solution

In order to check if `s` can be segmented, substrings of `s` might be checked if they can be segmented. These substrings will be formed by dropping chars off the left. We'll keep track of unsegmentable substring lengths to avoid checking if a substring is segmentable if was already determined to be unsegmentable. For example, in example 3, "og" would be checked if it's segmentable multiple times if we didn't keep track of unsegmentable substring lengths. For a string with 2 chars, checking multiple times is a small amount of work but the longer the string, the more work that has to be done.

```kotlin
fun wordBreak(s: String, wordDict: List<String>): Boolean {
    val wordSet = wordDict.toSet()
    val unsegmentableSubstringLengths = HashSet<Int>()
    
    fun isSegmentable(s2: String): Boolean {
        if (s2 in wordSet) return true
        
        for (i in 1 until s2.length) {
            if (s2.length - i in unsegmentableSubstringLengths || s2.take(i) !in wordSet) {
                continue
            }
            val substringToCheck = s2.drop(i)
            if (isSegmentable(substringToCheck)) return true
            unsegmentableSubstringLengths.add(substringToCheck.length)
        }
        
        return false
    }
    
    return isSegmentable(s)
}
```
