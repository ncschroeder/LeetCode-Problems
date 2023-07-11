/*
https://leetcode.com/problems/reverse-vowels-of-a-string/

Given a string `s`, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

Example:
Input: s = "leetcode"
Output: "leotcede"
*/

fun reverseVowels(s: String): String {
    val vowels: Set<Char> = "aeiouAEIOU".toSet()
    val sb = StringBuilder(s.length)

    /*
    Let vowelIndex start at the last index + 1, so that the 1st time a vowel is found, we search for a vowel
    starting at the end of s and work our way towards the front. The next time a vowel is found, we search
    for a vowel starting at the index before where a vowel was previously found.
    */
    var vowelIndex: Int = s.lastIndex + 1

    for (c: Char in s) {
        if (c in vowels) {
            vowelIndex = (vowelIndex - 1 downTo 0).first { s[it] in vowels }
            sb.append(s[vowelIndex])
        } else {
            sb.append(c)
        }
    }
    
    return sb.toString()
}