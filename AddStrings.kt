/*
https://leetcode.com/problems/add-strings/

Given two non-negative integers, `num1` and `num2` represented as string, return the sum of `num1` and `num2` as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.
*/

/*
To solve this, we do the same thing that we would do if we were adding these 2 numbers without using a calculator.
Start with the rightmost digit of both numbers and get the sum of these. If this is < 10, then prepend it to the
result. If the sum is >= 10, then prepend the last digit of it to the result. Then get the digits that are to 
the left and do the same thing but add 1 to this if the sum was >= 10.

If the strings are the same length, then we just do this for all digits, add 1 if applicable, and we're done.
If the strings are different lengths, once we get to the point where we've iterated through all the digits
of 1 string but not the other, take the digits of the string that has those remaining digits, add 1 to this
if applicable, and then prepend this to the result and we're done.
*/

fun addStrings(num1: String, num2: String): String {
    // Index variables for iterating backwards through the strings
    var i1: Int = num1.lastIndex
    var i2: Int = num2.lastIndex

    val answerBuilder = StringBuilder()
    var add1 = false

    while (i1 >= 0 && i2 >= 0) {
        val digitsSum: Int =
            num1[i1].digitToInt() + num2[i2].digitToInt() + (if (add1) 1 else 0)
        add1 = digitsSum >= 10

        // Prepend last digit of digitsSum
        answerBuilder.insert(0, digitsSum % 10)

        i1--
        i2--
    }

    if (num1.length == num2.length) {
        if (add1) {
            answerBuilder.insert(0, 1)
        }
    } else {
        // Get remaining digits from the longer param string
        var remainingDigits: String =
            if (i1 >= 0) num1.substring(0..i1) else num2.substring(0..i2)
        
        if (add1) {
            /*
            This is similar as what to do for the Plus One challenge.
            1st, find the index of the last digit that isn't a 9. This is a digit that needs to be incremented.
            If the string only contains 9s then set remainingDigits to a string that is 1 digit longer than it
            currently is and has 1 as its 1st digit and 0s for the rest. Otherwise, make remainingDigits have the
            same digits it currently has before incrementIndex followed by the digit at incrementIndex incremented
            followed by 0s.
            */
            val incrementIndex: Int = remainingDigits.indexOfLast { it != '9' }
            remainingDigits =
                if (incrementIndex == -1) {
                    "1".padEnd(length = remainingDigits.length + 1, padChar = '0')
                } else {
                    remainingDigits.take(incrementIndex)
                    .plus(remainingDigits[incrementIndex].digitToInt() + 1)
                    .padEnd(length = remainingDigits.length, padChar = '0')
                }
        }

        answerBuilder.insert(0, remainingDigits)
    }

    return answerBuilder.toString()
}