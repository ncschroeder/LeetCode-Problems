/*
https://leetcode.com/problems/isomorphic-strings/

Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

Constraints:
- 1 <= s.length <= 5 * 10^4
- t.length == s.length
- s and t consist of any valid ascii character
*/

fun isIsomorphic(s: String, t: String): Boolean {
    val charMap = mutableMapOf<Char, Char>()

    for (( index, sChar ) in s.withIndex()) {
        val tChar = t[index]
        val mappedChar: Char? = map[sChar]
        if (mappedChar == null) {
            // Check if a char is already mapped to tChar
            if (charMap.containsValue(tChar)) {
                return false
            }

            charMap[sChar] = tChar
        } else if (mappedChar != tChar) {
            return false
        }
    }

    return true
}