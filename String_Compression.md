## String Compression
### Difficulty: Medium
### [Link](https://leetcode.com/problems/string-compression/)

### Description

Given an array of characters `chars`, compress it using the following algorithm:

Begin with an empty string `s`. For each group of consecutive repeating characters in `chars`:
- If the group's length is 1, append the character to `s`.
- Otherwise, append the character followed by the group's length.

The compressed string `s` should not be returned separately, but instead, be stored in the input character array `chars`. Note that group lengths that are 10 or longer will be split into multiple characters in `chars`.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.

### Example 1

#### Input
`chars = ["a","a","b","b","c","c","c"]`

#### Output
Return `6`, and the first 6 characters of the input array should be: `["a","2","b","2","c","3"]`.

#### Explanation
The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

### Example 2

#### Input
`chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]`

#### Output
Return `4`, and the first 4 characters of the input array should be: `["a","b","1","2"]`.

#### Explanation
The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".

### Constraints
- `1 <= chars.length <= 2000`
- `chars[i]` is a lowercase English letter, uppercase English letter, digit, or symbol.

### Solution

```kotlin
fun compress(chars: CharArray): Int {
    // trackedChar is for chars in the chars array that we keep track of to see how many times they appear in a row.
    var trackedChar = chars.first()
    var trackedCharCount = 0
    var insertIndex = 0

    fun insertTrackedCharAndCount() {
        chars[insertIndex++] = trackedChar
        if (trackedCharCount > 1) {
            for (digit: Char in trackedCharCount.toString()) {
                chars[insertIndex++] = digit
            }
        }
    }

    for (char in chars) {
        if (char == trackedChar) {
            trackedCharCount++
        } else {
            insertTrackedCharAndCount()
            trackedChar = char
            trackedCharCount = 1
        }
    }
    
    insertTrackedCharAndCount()
    return insertIndex
}
```