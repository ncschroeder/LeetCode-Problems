/*
https://leetcode.com/problems/expressive-words/

Sometimes people repeat letters to represent extra feeling. For example:
- "hello" -> "heeellooo"
- "hi" -> "hiiii"

In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".

You are given a string `s` and an array of query strings `words`. A query word is stretchy if it can be made to be equal to `s` by any number of applications of the following extension operation: choose a group consisting of characters `c`, and add some number of characters `c` to the group so that the size of the group is three or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a size less than three. Also, we could do another extension like "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = `s`.

Return the number of query strings that are stretchy.

Example:
Input: s = "heeellooo", words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
*/

fun expressiveWords(s: String, words: Array<String>): Int =
    words.count { isStretchy(s, it) }

fun isStretchy(s: String, word: String): Boolean {
    // s index variables
    var si1 = 0
    var si2: Int?

    // word index variables
    var wi1 = 0
    var wi2: Int?

    while (true) {
        val char = s[si1]
        if (word[wi1] != char) {
            return false
        }
        
        si2 = (si1 + 1..s.lastIndex).firstOrNull { s[it] != char }
        wi2 = (wi1 + 1..word.lastIndex).firstOrNull { word[it] != char }
        
        // Find how many times in a row the char appears
        val sCharCount: Int = (si2 ?: s.length) - si1
        val wordCharCount: Int = (wi2 ?: word.length) - wi1

        // If the char appears more times in a row in the word than in s, the word is not stretchy
        if (wordCharCount > sCharCount) {
            return false
        }

        // If the char appears less than 3 times in a row in s, the word is possibly stretchy if the
        // char appears the same amount of times in a row in the word
        if (sCharCount < 3 && sCharCount != wordCharCount) {
            return false
        }
        
        // If si2 is null, then all of s has been searched. If wi2 is null, then all of the word has been
        // searched. These should become null at the same time in order for the word to be considered stretchy.
        if (si2 == null) {
            return wi2 == null
        }
        
        if (wi2 == null) {
            return false
        }

        si1 = si2
        wi1 = wi2
    }
}