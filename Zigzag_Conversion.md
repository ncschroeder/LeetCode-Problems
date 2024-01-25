## Zigzag Conversion
### Difficulty: Medium
### [Link](https://leetcode.com/problems/zigzag-conversion/)

### Description

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
```
P   A   H   N
A P L S I I G
Y   I   R
```

The 1<sup>st</sup> column contains the text "PAY", the 2<sup>nd</sup> column contains "P", the 3<sup>rd</sup> column contains "ALI", and so on. This visualization is read line by line as "PAHNAPLSIIGYIR". The 1<sup>st</sup> line contains the text "PAHN", the 2<sup>nd</sup> line contains "APLSIIG", and the 3<sup>rd</sup> line contains "YIR".

Write the code that will take a string and make this conversion given a number of rows.

For the example above, the input is `s = "PAYPALISHIRING", numRows = 3` and the output is `"PAHNAPLSIIGYIR"`.

### Another Example

#### Input
`s = "PAYPALISHIRING", numRows = 4`

#### Output
`"PINALSIGYAHRPI"`

#### Explanation

```
P     I    N
A   L S  I G
Y A   H R
P     I
```

### Constraints
- `1 <= s.length <= 1000`
- `s` consists of English letters (lower-case and upper-case), `','` and `'.'`.
- `1 <= numRows <= 1000`

### Refactored Solution

```kotlin
fun convert(s: String, numRows: Int): String {
    if (numRows == 1) {
        /*
        This is a special situation since the loop below will either increment or decrement sbIndex but we don't want either
        of those to happen if there's only 1 row. This also happens to be a situation where we just return the param string.
        */
        return s
    }

    // Let rowSbs be a list of string builders that will store the text that will go in each row.
    val rowSbs: List<StringBuilder> = List(size = numRows, init = { StringBuilder() })

    var sbIndex = 0

    // This'll be false when we want to decrement sbIndex.
    var incrementSbIndex = true

    for (char in s) {
        rowSbs[sbIndex].append(char)

        incrementSbIndex =
            if (incrementSbIndex) sbIndex < rowSbs.lastIndex
            else sbIndex == 0

        sbIndex += if (incrementSbIndex) 1 else -1
    }

    return rowSbs.joinToString(separator = "")
}
```

### Original Solution

```kotlin
fun convert(s: String, numRows: Int): String {
    if (numRows == 1) {
        /*
        This is a special situation since the loop below will decrement sbIndex by 2 and that will cause sbIndex
        to be -1 if numRows is 1. This also happens to be a situation where we just return the param string.
        */
        return s
    }
    
    // Let sbs be a list of string builders that will store the text that will go in each row.
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
```