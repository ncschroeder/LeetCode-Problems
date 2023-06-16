/*
https://leetcode.com/problems/greatest-common-divisor-of-strings/

For two strings `s` and `t`, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).

Given two strings `str1` and `str2`, return the largest string `x` such that `x` divides both `str1` and `str2`.

Example:
Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
*/

// Original solution
fun gcdOfStrings1(str1: String, str2: String): String {
    for (i in Math.min(str1.length, str2.length) downTo 1) {
        val quotient1: Double = str1.length.toDouble() / i
        val quotient2: Double = str2.length.toDouble() / i

        // Check if the quotients are whole to see if both param string lengths are divisible by i
        if (quotient1 != Math.floor(quotient1) || quotient2 != Math.floor(quotient2)) {
            continue
        }

        val str1Short = str1.take(i)
        val str2Short = str2.take(i)
        if (str1Short == str2Short && str1Short.repeat(quotient1.toInt()) == str1 && str2Short.repeat(quotient2.toInt()) == str2) {
            return str1Short
        }
    }

    return ""
}


// Refactored solution
fun gcdOfStrings2(str1: String, str2: String): String =
    (Math.min(str1.length, str2.length) downTo 1)
    .asSequence()
    .filter { str1.length % it == 0 && str2.length % it == 0 }
    .map { str1.take(it) }
    .firstOrNull {
        str2.startsWith(it) && it.repeat(str1.length / it.length) == str1 && it.repeat(str2.length / it.length) == str2
    }
    ?: ""