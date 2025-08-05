## Letter Combinations of a Phone Number :telephone:
### Difficulty: Medium
### [Link](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)

### Description

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

![Phone Digits and Letters](/Images/phone_digits_and_letters.JPG)

### Example 1

#### Input
`digits = "23"`

#### Output
`["ad","ae","af","bd","be","bf","cd","ce","cf"]`

### Example 2

#### Input
`digits = ""`

#### Output
`[]`

### Example 3

#### Input
`digits = "2"`

#### Output
`["a","b","c"]`

### Constraints

- `0 <= digits.length <= 4`
- `digits[i]` is a digit in the range `['2', '9']`.

### My 1<sup>st</sup> Solution

```kotlin
class Solution {
    val digitsAndLetters: Map<Char, String> =
        arrayOf("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
        .withIndex()
        .associate { (index, letters) -> '2' + index to letters }
    
    fun letterCombinations(digits: String): List<String> {        
        when (digits.length) {
            0 -> return emptyList()
            
            1 -> return digitsAndLetters.getValue(digits.first()).map { it.toString() }
        }
        
        val combosAfterDrop: List<String> = letterCombinations(digits.drop(1))
        val newCombos = ArrayList<String>()
        
        for (letter in digitsAndLetters.getValue(digits.first())) {
            for (combo in combosAfterDrop) {
                newCombos.add(letter + combo)
            }
        }
        
        return newCombos
    }
}
```

### My 2<sup>nd</sup> Solution

```kotlin
class Solution {
    val digitsAndLetters: Map<Char, String> =
        arrayOf("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
        .withIndex()
        .associate { (index, letters) -> '2' + index to letters }
    
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()
        
        var combosSoFar = arrayListOf("")
        
        for (digit: Char in digits) {
            val newCombosSoFar = ArrayList<String>()
            
            for (letter in digitsAndLetters.getValue(digit)) {
                for (combo in combosSoFar) {
                    newCombosSoFar.add(combo + letter)
                }
            }
            
            combosSoFar = newCombosSoFar
        }
        
        return combosSoFar
    }
}
```