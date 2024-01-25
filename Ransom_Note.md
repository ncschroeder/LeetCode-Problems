## Ransom Note
### Difficulty: Easy
### [Link](https://leetcode.com/problems/ransom-note/)

### Description

Given two strings `ransomNote` and `magazine`, return `true` if `ransomNote` can be constructed by using the letters from `magazine` and `false` otherwise.

Each letter in `magazine` can only be used once in `ransomNote`.

### Example 1

#### Input
`ransomNote = "aa", magazine = "aab"`

#### Output
`true`

### Example 2

#### Input
`ransomNote = "aa", magazine = "ab"`

#### Output
`false`

### Constraints
- <code>1 <= ransomNote.length, magazine.length <= 10<sup>5</sup></code>
- `ransomNote` and `magazine` consist of lowercase English letters.

### Solution with Lazy Searching of the Magazine

```kotlin
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    /*
    Let magLetterCounts be a map where the keys are letters we find when searching the magazine but don't need right away.
    Each value is the count of that letter so far minus the amount we use.
    */
    val magLetterCounts = HashMap<Char, Int>()
    val magIterator: Iterator<Char> = magazine.iterator()
    
    for (noteLetter: Char in ransomNote) {
        when (val magLetterCount: Int? = magLetterCounts[noteLetter]) {
            null -> {
                while (true) {
                    if (!magIterator.hasNext()) return false
                    val magLetter: Char = magIterator.next()
                    if (magLetter == noteLetter) break
                    magLetterCounts[magLetter] = (magLetterCounts[magLetter] ?: 0) + 1
                }
            }

            1 -> magLetterCounts.remove(noteLetter)
            
            else -> magLetterCounts[noteLetter] = magLetterCount - 1
        }
    }

    return true
}
```

### Solution with Eager Searching of the Magazine

```kotlin
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    /*
    Let remainingMagLetterCounts be a map that starts off with keys for the letters in the magazine and values for 
    the counts of those letters in the magazine.
    */
    val remainingMagLetterCounts: MutableMap<Char, Int> =
        magazine
        .groupingBy { it }
        .eachCountTo(HashMap())
    
    for (noteLetter: Char in ransomNote) {
        when (val magLetterCount: Int? = remainingMagLetterCounts[noteLetter]) {
            null -> return false

            1 -> remainingMagLetterCounts.remove(noteLetter)
            
            else -> remainingMagLetterCounts[noteLetter] = magLetterCount - 1
        }
    }

    return true
}
```