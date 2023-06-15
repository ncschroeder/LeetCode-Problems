/*
https://leetcode.com/problems/longest-common-prefix/

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Constraints:
- 1 <= strs.length <= 200
- 0 <= strs[i].length <= 200
- strs[i] consists of only lowercase English letters

Example:
Input: strs = ["flower","flow","flight"]
Output: "fl"
*/

// Original solution
fun longestCommonPrefix1(strings: Array<String>): String {
    var longestCommonPrefix = ""
    for (i in 0 until 200) {
        val c: Char? = strs.first().getOrNull(i)
        if (c == null || strs.any { it.getOrNull(i) != c }) {
            break
        }
        longestCommonPrefix += c
    }

    return longestCommonPrefix
}


// Refactored solution
fun longestCommonPrefix2(strings: Array<String>): String =
    strings.first().asSequence().withIndex()
    .takeWhile { ( index, char ) -> strings.all { it.getOrNull(index) == char } }
    .map { ( _, char ) -> char }
    .joinToString(separator = "")
