## Number of Days Between 2 Dates :date:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/number-of-days-between-two-dates/)

### Description

Write a program to count the number of days between two dates.

The two dates are given as strings, their format is `YYYY-MM-DD` as shown in the examples.

### Example 1

#### Input
`date1 = "2019-06-29", date2 = "2019-06-30"`

#### Output
`1`

### Example 2

#### Input
`date1 = "2020-01-15", date2 = "2019-12-31"`

#### Output
`15`

### Constraints
- The given dates are valid dates between the years 1971 and 2100.

### Solution

```kotlin
class Solution {
    fun daysBetweenDates(date1String: String, date2String: String): Int {
        val date1 = Date(date1String)
        val date2 = Date(date2String)
        
        return when {
            date1.year != date2.year -> daysBetweenDatesWithDifferentYears(date1, date2)
            
            date1.month != date2.month -> daysBetweenDatesWithDifferentMonths(date1, date2)
            
            else -> abs(date1.day - date2.day)
        }
    }

    class Date(dateString: String) {
        val year: Int
        val month: Int
        val day: Int
                
        init {
            val dateParts: List<Int> = dateString.split('-').map { it.toInt() }
            year = dateParts[0]
            month = dateParts[1]
            day = dateParts[2]
        }
    }

    val monthDayCounts = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    fun isLeapYear(year: Int): Boolean =
        year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)

    fun daysBetweenDatesWithDifferentYears(date1: Date, date2: Date): Int {
        val earlierDate = if (date1.year < date2.year) date1 else date2
        val laterDate = if (earlierDate === date1) date2 else date1
                
        val numEarlierYearDays: Int =
            (monthDayCounts[earlierDate.month - 1] - earlierDate.day + 1) +
            monthDayCounts.drop(earlierDate.month).sum() +
            if (earlierDate.month <= 2 && isLeapYear(earlierDate.year)) 1 else 0

        val numDaysFromYearsInBetween: Int =
            (earlierDate.year + 1 until laterDate.year)
            .sumOf { (if (isLeapYear(it)) 366 else 365) as Int }

        /*
        There are 2 sumOf functions, one whose param function returns an int and one whose param
        function returns a long. If I don't cast the result of the if-else expression above to
        an int, the compiler doesn't know which sumOf function I'm trying to call, which doesn't
        make sense since 366 and 365 are int literals so the compiler seemingly should be able
        to infer that I'm trying to call the sumOf function whose param function returns an int.
        */
            
        val numLaterYearDays: Int =
            monthDayCounts.take(laterDate.month - 1).sum() +
            (laterDate.day - 1) +
            if (laterDate.month > 2 && isLeapYear(laterDate.year)) 1 else 0
        
        return numEarlierYearDays + numDaysFromYearsInBetween + numLaterYearDays
    }

    fun daysBetweenDatesWithDifferentMonths(date1: Date, date2: Date): Int {
        val earlierDate = if (date1.month < date2.month) date1 else date2
        val laterDate = if (earlierDate === date1) date2 else date1
        
        // First, find the number of non-leap days.
        
        val numEarlierMonthDays: Int =
            monthDayCounts[earlierDate.month - 1] - earlierDate.day + 1
        
        val numDaysFromMonthsInBetween: Int =
            monthDayCounts
            .slice(earlierDate.month until laterDate.month - 1)
            .sum()
            
        val numLaterMonthDays: Int = laterDate.day - 1
            
        val addLeapDay: Boolean =
            earlierDate.month <= 2 && laterDate.month > 2 && isLeapYear(earlierDate.year)
        
        return numEarlierMonthDays + numDaysFromMonthsInBetween + numLaterMonthDays + if (addLeapDay) 1 else 0
    }
}
```