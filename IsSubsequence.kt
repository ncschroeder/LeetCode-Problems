/*
https://leetcode.com/problems/is-subsequence/

Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
*/

fun isSubsequence(s: String, t: String): Boolean {
    // Start charIndex at -1 so that the search start index is 0 on the 1st iteration
    var charIndex = -1
    for (c in s) {
        charIndex = t.indexOf(char = c, startIndex = charIndex + 1)
        if (charIndex == -1) {
            return false
        }
    }
    return true
}