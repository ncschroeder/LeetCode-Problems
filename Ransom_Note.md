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
    Let magCharCounts be a map where the keys are letters we find when searching the magazine but don't need right away.
    The values are the counts of how many of each letter we find minus the amount we use.
    */
    val magCharCounts = HashMap<Char, Int>()
    val magIterator: Iterator<Char> = magazine.iterator()
    
    for (noteChar in ransomNote) {
        when (val noteCharCount: Int? = magCharCounts[noteChar]) {
            null -> {
                while (true) {
                    if (!magIterator.hasNext()) return false
                    val magChar = magIterator.next()
                    if (magChar == noteChar) break
                    magCharCounts[magChar] = (magCharCounts[magChar] ?: 0) + 1
                }
            }

            1 -> magCharCounts.remove(noteChar)
            
            else -> magCharCounts[noteChar] = noteCharCount - 1
        }
    }

    return true
}
```

### Original Solution with Eager Searching of the Magazine

```kotlin
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    /*
    Let magCharCounts be a map where the keys are the letters in the magazine and the values are the counts for how
    many times that letter appears in the magazine.
    */
    val magCharCounts = HashMap<Char, Int>()
    
    for (c: Char in magazine) {
        magCharCounts[c] = (magCharCounts[c] ?: 0) + 1
    }

    for (c: Char in ransomNote) {
        when (val charCount: Int? = magCharCounts[c]) {
            null -> return false

            1 -> magCharCounts.remove(c)

            else -> magCharCounts[c] = charCount - 1
        }
    }

    return true
}
```