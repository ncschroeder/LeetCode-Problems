/*
https://leetcode.com/problems/shortest-palindrome/

You are given a string `s`. You can convert `s` to a palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.

Examples:
Input: s = "aacecaaa"
Output: "aaacecaaa"

Input: s = "abcd"
Output: "dcbabcd"
*/

/*
Find the longest palindrome that can be made by dropping chars from the right. Start by "dropping" 0 chars. When a palindrome is found:
get a string of the dropped chars, reverse it, and prepend it to the param string. When we drop all chars besides the 1st one, the string
formed as a result will always be a palindrome.
*/

fun String.isPalindrome(): Boolean =
    (0 until length / 2).all { this[it] == this[lastIndex - it] }

// Original solution
fun shortestPalindrome1(s: String): String {
    if (s.length in 0..1) {
        return s
    }
                                            // isPalindrome was a normal function for this solution, instead of an extension function
    val i: Int? = (0..s.length - 2).firstOrNull { isPalindrome(s.dropLast(it)) }
    if (i != null) {
        return s.takeLast(i).reversed() + s
    }

    val j: Int = (1..s.lastIndex).first { s[it] != s.first() }
    return s.drop(j).reversed() + s
}

// Refactored solution
fun shortestPalindrome2(s: String): String =
    if (s.isEmpty()) s
    else (s.length downTo 1)
        .first { s.take(it).isPalindrome() }
        .let { s.drop(it).reversed() + s }