/*
This is a challenge I got in an interview. Write a function with an int param. If that int is negative,
return the number of 2s in all digits in the range of 0 down to that number. If the int is positive, return
the number of 2s in all digits in the range of 0 up to that number.


Here's the solution I gave in the interview in JavaScript. I actually kept this in a file on my computer.

function numberOf2s(number) {
    let countOf2s = 0;
    if (number > 0) {
        for (let i = 0; i <= number; i++) {
            const numberString = i.toString();
            for (const digitString of numberString) {
                if (digitString === '2') {
                    countOf2s++;
                }
            }
        }
    } else if (number < 0) {
        for (let i = 0; i >= number; i--) {
            const numberString = i.toString();
            for (const digitString of numberString) {
                if (digitString === '2') {
                    countOf2s++;
                }
            }
        }
    }
    
    return countOf2s;
}
*/


// I didn't realize that I could've used the absolute value of the param. I could've done something like this...
fun countOf2s2(i: Int): Int {
    var count = 0
    for (j: Int in 2..i.absoluteValue) {
        for (digit: Char in j.toString()) {
            if (digit == '2') {
                count++
            }
        }
    }
    return count
}


// ...or better yet, this (though not in JavaScript, to my knowlege)
fun countOf2s3(i: Int): Int =
    (2..i.absoluteValue)
    .asSequence()
    .flatMap { j: Int -> j.toString().asIterable() } // At this point, we have a Sequence of characters of the digits of all ints in the range
    .count { digit: Char -> digit == '2' }
