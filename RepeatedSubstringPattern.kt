/*
https://leetcode.com/problems/repeated-substring-pattern/

Given a string `s`, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
*/

fun repeatedSubstringPattern(s: String): Boolean =
    (1..s.length / 2).any { s.length % it == 0 && s.take(it).repeat(s.length / it) == s }