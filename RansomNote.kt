/*
https://leetcode.com/problems/ransom-note/

Given two strings `ransomNote` and `magazine`, return `true` if `ransomNote` can be constructed by using the letters from `magazine` and `false` otherwise.

Each letter in `magazine` can only be used once in `ransomNote`.
*/

// Original solution with eager searching of the magazine
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    // Let magazineCharCounts be a map where the keys are the letters in magazine and the values are the counts
    // for how many times that letter appears in magazine
    val magazineCharCounts = mutableMapOf<Char, Int>()
    for (c in magazine) {
        magazineCharCounts[c] = (magazineCharCounts[c] ?: 0) + 1
    }

    for (c in ransomNote) {
        when (val charCount: Int? = magazineCharCounts[c]) {
            null -> return false

            1 -> magazineCharCounts.remove(c)

            else -> magazineCharCounts[c] = charCount - 1
        }
    }

    return true
}


// Solution with lazy searching of the magazine
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val magazineCharCounts = mutableMapOf<Char, Int>()
    var searchIndex = 0

    for (c in ransomNote) {
        when (val charCount: Int? = magazineCharCounts[c]) {
            null -> {
                if (searchIndex > magazine.lastIndex) {
                    return false
                }

                while (true) {
                    val curChar = magazine[searchIndex]
                    
                    if (curChar == c) {
                        searchIndex++
                        break
                    }

                    if (searchIndex == magazine.lastIndex) {
                        return false
                    }
                    
                    magazineCharCounts[curChar] = (magazineCharCounts[curChar] ?: 0) + 1
                    searchIndex++
                }
            }

            1 -> magazineCharCounts.remove(c)
            
            else -> magazineCharCounts[c] = charCount - 1
        }
    }

    return true
}