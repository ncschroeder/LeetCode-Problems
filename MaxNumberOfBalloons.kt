/*
https://leetcode.com/problems/maximum-number-of-balloons/

Given a string `text`, you want to use the characters of `text` to form as many instances of the word "balloon" as possible.

You can use each character in `text` at most once. Return the maximum number of instances that can be formed.
*/

fun maxNumberOfBalloons(text: String): Int {
    // Let charCounts be a map where the keys are the letters of the word "balloon" and the values start at 0
    // and are for the counts for how many times that letter appears in the text
    val charCounts: MutableMap<Char, Int> = 
        "balon".associateWithTo(destination = mutableMapOf(), valueSelector = { 0 })
    
    // Each time a letter that is in the word "balloon" is found, increment its value
    for (c in text) {
        charCounts[c]?.let { charCounts[c] = it + 1 }
    }

    return charCounts.minOf { ( char, count ) -> if (char == 'l' || char == 'o') count / 2 else count }
}