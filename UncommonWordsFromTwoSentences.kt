/*
https://leetcode.com/problems/uncommon-words-from-two-sentences/

A sentence is a string of single-space separated words where each word consists only of lowercase letters.

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Given two sentences `s1` and `s2`, return a list of all the uncommon words. You may return the answer in any order.

Constraints:
- 1 <= s1.length, s2.length <= 200
- s1 and s2 consist of lowercase English letters and spaces
- s1 and s2 do not have leading or trailing spaces
- All the words in s1 and s2 are separated by a single space
*/

fun uncommonFromSentences(s1: String, s2: String): Array<String> {
    fun getWordsStuff(sentence: String): Pair<Set<String>, Sequence<String>> {
        val allWords = mutableSetOf<String>()
        val wordsThatAppearOnce = mutableSetOf<String>()
        
        for (word: String in sentence.splitToSequence(" ")) {
            if (word !in allWords) {
                allWords.add(word)
                wordsThatAppearOnce.add(word)
            } else if (word in wordsThatAppearOnce) {
                wordsThatAppearOnce.remove(word)
            }
        }

        return Pair(allWords, wordsThatAppearOnce.asSequence())
    }

    val ( allWords1, wordsThatAppearOnce1 ) = getWordsStuff(s1)
    val ( allWords2, wordsThatAppearOnce2 ) = getWordsStuff(s2)

    return wordsThatAppearOnce1.minus(allWords2)
        .plus(wordsThatAppearOnce2.minus(allWords1))
        .toList()
        .toTypedArray()
}