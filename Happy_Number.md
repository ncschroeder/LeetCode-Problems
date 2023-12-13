## Happy Number :smiley:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/happy-number/)

### Description
Write an algorithm to determine if a number `n` is happy.

A *happy number* is a number defined by the following process:
- Starting with any positive integer, replace the number by the sum of the squares of its digits.
- Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
- Those numbers for which this process ends in 1 are happy.

Return `true` if `n` is a happy number, and `false` if not.

### Example 1

#### Input
`n = 19`

#### Output
`true`

#### Explanation
$1^2 + 9^2 = 82$

$8^2 + 2^2 = 68$

$6^2 + 8^2 = 100$

$1^2 + 0^2 + 0^2 = 1$

### Example 2

#### Input
`n = 2`

#### Output
`false`

#### Explanation
$2^2 = 4$

$4^2 = 16$

$1^2 + 6^2 = 37$

$3^2 + 7^2 = 58$

$5^2 + 8^2 = 89$

$8^2 + 9^2 = 145$

$1^2 + 4^2 + 5^2 = 42$

$4^2 + 2^2 = 20$

$2^2 + 0^2 = 4$

The 1<sup>st</sup> and 9<sup>th</sup> sums are 4, so there's an endless cycle.

### Constraints
- <code>1 <= n <= 2<sup>31</sup> - 1</code>

### Solution

```kotlin
fun isHappy(n: Int): Boolean {
    /*
    n would work fine for a variable name but in Kotlin, you can't reassign params. The variable i will be used
    for doing the process described above.
    */
    var i = n
    val processNumbers = HashSet<Int>()

    while (true) {
        when {
            i == 1 -> return true

            !processNumbers.add(i) -> return false
        }

        i =
            i.toString()
            .sumOf { digit: Char -> digit.digitToInt().let { it * it } }
    }
}
```