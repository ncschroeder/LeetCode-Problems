/*
https://leetcode.com/problems/keyboard-row/

Given an array of strings `words`, return the words that can be typed using letters of the alphabet on only one row of the American keyboard.

In the American keyboard:
- The first row consists of the characters "qwertyuiop"
- The second row consists of the characters "asdfghjkl"
- The third row consists of the characters "zxcvbnm"
*/
fun findWords(words: Array<String>): Array<String> {
    // Let letterSets be a List of 3 Char Sets where the 1st, 2nd, and 3rd Sets have the lowercase variants
    // of the letters in the 1st, 2nd, and 3rd rows of a keyboard, respectively
    val letterSets: List<Set<Char>> =
        arrayOf("qwertyuiop", "asdfghjkl", "zxcvbnm")
        .map { it.toSet() }

    return words.filter { word: String ->
        val letter1Lower: Char = word[0].lowercase()
        val letterSet: Set<Char> = letterSets.first { letter1Lower in it }
        // Have this lambda return true if the lowercase variants of all letters in the word are in letterSet
        word.all { it.lowercase() in letterSet }
    }.toTypedArray()
}