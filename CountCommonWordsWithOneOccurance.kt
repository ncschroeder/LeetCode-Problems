/*
https://leetcode.com/problems/count-common-words-with-one-occurrence/

Given two string arrays `words1` and `words2`, return the number of strings that appear exactly once in each of the two arrays.
*/

fun countWords(words1: Array<String>, words2: Array<String>): Int {
    fun getWordsThatAppearOnce(words: Array<String>): Set<String> {
        val wordsThatAppearOnce = mutableSetOf<String>()
        val removedWords = mutableSetOf<String>()

        for (word in words) {
            if (word in wordsThatAppearOnce) {
                wordsThatAppearOnce.remove(word)
                removedWords.add(word)
            } else if (word !in removedWords) {
                wordsThatAppearOnce.add(word)
            }
        }

        return wordsThatAppearOnce
    }

    val s1 = getWordsThatAppearOnce(words1)
    return getWordsThatAppearOnce(words2).count { it in s1 }
}