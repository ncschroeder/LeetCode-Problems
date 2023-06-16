/*
https://leetcode.com/problems/group-anagrams/

Given an array of strings `strs`, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
*/

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    // 2 words are an anagram if they have the same characters and each character appears the same amount of times

    /*
    Let anagramGroups be a map where the keys are maps and the values are Lists of Strings that have the same
    chars as the keys of that map and each char appears in the string the same amount of times as the corresponding
    value in the map.
    */
    val anagramGroups = mutableMapOf<Map<Char, Int>, MutableList<String>>()

    for (s in strs) {
        // Let charCounts be a map where the keys are the chars that are in the string in the current iteration
        // and the values are the counts of how many times that char appears in that string
        val charCounts = mutableMapOf<Char, Int>()
        for (c in s) {
            charCounts[c] = (charCounts[c] ?: 0) + 1
        }

        val anagrams: MutableList<String>? = anagramGroups[charCounts]
        if (anagrams != null) {
            anagrams.add(s)
        } else {
            anagramGroups[charCounts] = mutableListOf(s)
        }
    }

    return anagramGroups.values.map { it as List<String> }
}