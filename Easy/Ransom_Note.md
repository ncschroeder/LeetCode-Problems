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

### Refactored Solution with Eager Searching of the Magazine

This solution searches the whole magazine, whether it needs to or not.

```kotlin
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    /*
    Let remainingMagCharCounts be a map with keys for the letters in the
    magazine and values for the counts of those letters in the magazine.
    */
    val remainingMagCharCounts: MutableMap<Char, Int> =
        magazine
        .groupingBy { it }
        .eachCountTo(HashMap())
    
    for (noteChar in ransomNote) {
        val magCharCount: Int = remainingMagCharCounts[noteLetter] ?: 0
        if (magCharCount == 0) return false
        remainingMagCharCounts[noteLetter] = magCharCount - 1
    }
    
    return true
}
```

### Solution with Lazy Searching of the Magazine

This solution only searches the magazine as needed.

```kotlin
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    /*
    Let magCharCounts be a map with keys for letters we find when searching the magazine but don't
    need right away. Each value is the count of that letter so far minus the amount we used.
    */
    val magCharCounts = HashMap<Char, Int>()
    val magIterator: Iterator<Char> = magazine.iterator()
    
    for (noteChar in ransomNote) {
        val magCharCount: Int = magCharCounts[noteChar] ?: 0
        
        if (magCharCount == 0) {
            while (true) {
                if (!magIterator.hasNext()) return false
                val magChar = magIterator.next()
                if (magChar == noteChar) break
                magCharCounts[magChar] = (magCharCounts[magChar] ?: 0) + 1
            }
        } else {
            magCharCounts[noteChar] = magCharCount - 1
        }
    }
    
    return true
}
```

### My 1<sup>st</sup> Solution (Eager Searching)

```kotlin
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val magazineCharCounts = mutableMapOf<Char, Int>()
    for (c in magazine) {
        magazineCharCounts[c] = (magazineCharCounts[c] ?: 0) + 1
    }
    
    for (c in ransomNote) {
        val charCount: Int? = magazineCharCounts[c]
        if (charCount == null) {
            return false
        }
        
        if (charCount == 1) {
            magazineCharCounts.remove(c)
        } else {
            magazineCharCounts[c] = charCount - 1
        }
    }
    
    return true
}
```