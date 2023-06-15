/*
https://leetcode.com/problems/valid-anagram/

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
*/

// Extension function that returns a map where the keys are the chars that appear in this string and the values
// are the counts for how many times that char appears in this string.
fun String.getCharCounts(): Map<Char, Int> {
    val counts = mutableMapOf<Char, Int>()
    for (c in this) {
        counts[c] = (counts[c] ?: 0) + 1
    }
    return counts
}

// 2 words are anagrams if they contain the same chars and each char appears the same amount of times.
// The checking of the lengths isn't necessary but is done to easily improve performance.
fun isAnagram(s: String, t: String): Boolean =
    s.length == t.length && s.getCharCounts() == t.getCharCounts()