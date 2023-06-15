/*
https://leetcode.com/problems/zigzag-conversion/

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows.

Another example:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
*/

// Original solution
fun convert1(s: String, numRows: Int): String {
    if (numRows == 1) {
        // This is a special situation since the loop below will decrement sbIndex by 2 and that will
        // cause sbIndex to be -1 if numRows is 1
        return s
    }
    
    // Let sbs be a List of empty StringBuilders
    val sbs: List<StringBuilder> = List(size = numRows, init = { StringBuilder() })
    var sbIndex = 0
    val sIterator: Iterator<Char> = s.iterator()

    while (true) {
        while (sbIndex < numRows) {
            if (!sIterator.hasNext()) {
                return sbs.joinToString(separator = "")
            }
            sbs[sbIndex].append(sIterator.next())
            sbIndex++
        }

        sbIndex -= 2
        while (sbIndex > 0) {
            if (!sIterator.hasNext()) {
                return sbs.joinToString(separator = "")
            }
            sbs[sbIndex].append(sIterator.next())
            sbIndex--
        }
    }
}


// Refactored solution
fun convert2(s: String, numRows: Int): String {
    if (numRows == 1) {
        // This is a special situation since the loop below will either increment or decrement sbIndex
        // but we don't want either of those to happen if there's only 1 row
        return s
    }

    // Let sbs be a List of empty StringBuilders
    val sbs: List<StringBuilder> = List(size = numRows, init = { StringBuilder() })
    var sbIndex = 0

    // Have this be true when we want to increment sbIndex and be false when we want to decrement it
    var incrementSbIndex = true

    for (c in s) {
        sbs[sbIndex].append(c)
        incrementSbIndex =
            if (incrementSbIndex) sbIndex < sbs.lastIndex
            else sbIndex == 0
        sbIndex += if (incrementSbIndex) 1 else -1
    }

    return sbs.joinToString(separator = "")
}