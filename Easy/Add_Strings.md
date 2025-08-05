## Add Strings :heavy_plus_sign:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/add-strings/)

### Description

Given two non-negative integers, `num1` and `num2` represented as strings, return the sum of `num1` and `num2` as a string.

You must solve the problem without using any built-in library for handling large integers (such as `BigInteger`). You must also not convert the inputs to integers directly.

### Example 1

#### Input
`num1 = "11", num2 = "123"`

#### Output
`"134"`

### Example 2

#### Input
`num1 = "456", num2 = "77"`

#### Output
`"533"`

### Constraints

- <code>1 <= num1.length, num2.length <= 10<sup>4</sup></code>
- `num1` and `num2` consist of only digits.
- `num1` and `num2` don't have any leading zeros except for the zero itself.

### Solution

The algorithm used in this solution is similar to the algorithm for adding 2 numbers without using a calculator nor a programming language. :slightly_smiling_face: You know, the algorithm that kids in like 2<sup>nd</sup> grade learn, though they probably don't consider it an "algorithm". They probably think of it as "the way to do it" or something like that. :slightly_smiling_face:

#### Time Complexity

I'm going to use the variables $s$ and $l$ to represent the length of shortest and longest param strings, respectively. The while loop is a $O(s)$ operation, which is also a $O(l)$ operation if $s$ and $l$ are the same, of course. If applicable, the operation with the remaining digits is a $O(l - s)$ operation. $s + (l - s) = l$, so the time complexity is $O(l)$, whether the operation with the remaining digits is done or not.

Another way of looking at it is: the while loop is an operation proportional to some amount of digits in the longest string. If applicable, the operation with the remaining digits is proportional to the amount of digits in the longest string that weren't involved in the while loop.

```kotlin
fun addStrings(num1: String, num2: String): String {
    /*
    If both param numbers have the same number of digits and each digit is 9, then both numbers would
    be the highest number for that amount of digits. The sum of these is a number that's 1 digit longer
    than both of the param numbers. If we make 1 of the param numbers smaller, the sum of them is a
    number that's at most 1 digit longer than the param number with the most digits. As a result, the
    length of the answer is at most the max of the lengths of the 2 param numbers + 1, so set the
    capacity of answerBuilder to that. Since StringBuilder is a Java interop class, I can't write
    `capacity = ` before the `max(...) + 1`.
    */
    val answerBuilder = StringBuilder(max(num1.length, num2.length) + 1)
    
    /*
    Iterate backwards through the strings at the same time until we iterate through at least 1 of them.
    Append digits to the end of answerBuilder and reverse the contents when necessary.
    
    Let i1 and i2 be index variables for iterating and possibly extracting digits from 1 of the strings.
    */
    var i1: Int = num1.lastIndex
    var i2: Int = num2.lastIndex
    var add1 = false

    while (i1 >= 0 && i2 >= 0) {
        val digitsSum: Int =
            num1[i1--].digitToInt() + num2[i2--].digitToInt() + if (add1) 1 else 0
        
        add1 = digitsSum >= 10
        
        // Append last digit.
        answerBuilder.append(digitsSum % 10)
    }
    
    if (num1.length == num2.length) {
        return (
            answerBuilder
            .apply { if (add1) append(1) }
            .reverse()
            .toString()
        )
    }
    
    // Get remaining digits from the longer param string.
    var remainingDigits: String =
        if (i1 >= 0) num1.substring(0..i1)
        else num2.substring(0..i2)
    
    if (add1) {
        /*
        Set remainingDigits to the string representation of the number formed by incrementing the
        number currently in remainingDigits.
        
        First, find the index of the last digit that isn't a 9. This is a digit that needs to be
        incremented. If remainingDigits only contains 9's then set remainingDigits to a string that's
        1 digit longer than it currently is and has 1 as its first digit and 0's for the rest.
        Otherwise, make remainingDigits have the same digits it currently has before incrementIndex
        followed by the digit at incrementIndex incremented followed by 0's.
        */
        
        val incrementIndex: Int = remainingDigits.indexOfLast { it != '9' }
        
        remainingDigits =
            if (incrementIndex == -1) {
                "1".padEnd(length = remainingDigits.length + 1, padChar = '0')
            } else {
                remainingDigits
                .substring(0 until incrementIndex)
                .plus(remainingDigits[incrementIndex].digitToInt() + 1)
                .padEnd(length = remainingDigits.length, padChar = '0')
            }
    }
    
    return (
        answerBuilder
        .reverse()
        .insert(0, remainingDigits)
        .toString()
    )
}
```
