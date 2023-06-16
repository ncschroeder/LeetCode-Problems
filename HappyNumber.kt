/*
https://leetcode.com/problems/happy-number/

Write an algorithm to determine if a number `n` is happy.

A happy number is a number defined by the following process:
- Starting with any positive integer, replace the number by the sum of the squares of its digits.
- Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
- Those numbers for which this process ends in 1 are happy.

Return `true` if n is a happy number, and `false` if not.

Process examples:

2, 4, 16, 37, 58, 89, 145, 42, 20, 4, ...
   ^-------------------------------^
2 isn't happy

19, 82, 68, 100, 1
19 is happy
*/

fun isHappy(n: Int): Boolean {
    val processNumbers = mutableSetOf<Int>()
    
    var i = n
    while (true) {
        if (i == 1) {
            return true
        }

        if (i in processNumbers) {
            return false
        }

        processNumbers.add(i)

        i =
            i.toString()
            .sumOf { digit: Char -> digit.digitToInt().let { it * it }  }
    }
}