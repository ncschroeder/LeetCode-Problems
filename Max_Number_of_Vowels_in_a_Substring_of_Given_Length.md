## Max Number of Vowels in a Substring of Given Length
### Difficulty: Medium
### [Link](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/)

### Description
Given a string `s` and an integer `k`, return the maximum number of vowel letters in any substring of `s` with length `k`.

Vowel letters in English are a, e, i, o, and u.

### Example

#### Input
`s = "abciiidef", k = 3`

#### Output
`3`

#### Explanation
The substring "iii" contains 3 vowel letters.

### Constraints
- <code>1 <= s.length <= 10<sup>5</sup></code>
- `s` consists of lowercase English letters.
- `1 <= k <= s.length`

### Solution
The vowel counting at the beginning is a $O(k)$ operation and then the for loop is a $O(n - k)$ operation. $k + (n - k) = n$, so the time complexity is $O(n)$.

```kotlin
fun maxVowels(s: String, k: Int): Int {
    val vowels: Set<Char> = "aeiou".toSet()
    var curVowelCount: Int = s.take(k).count { it in vowels }
    var maxVowelCount = curVowelCount

    /*
    To find the number of vowels in the next substring of length k, decrement curVowelCount if the 1st char of the
    current substring is a vowel and increment curVowelCount if the last char of the next substring is a vowel.
    */
    
    for (i: Int in 0..s.lastIndex - k) {
        if (s[i] in vowels) curVowelCount--

        if (s[i + k] in vowels) {
            curVowelCount++
            maxVowelCount = Math.max(maxVowelCount, curVowelCount)
        }
    }
    
    return maxVowelCount
}
```