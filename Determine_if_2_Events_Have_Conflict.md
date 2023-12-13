## Determine if 2 Events Have Conflict :clock5:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/determine-if-two-events-have-conflict/)

### Description
You are given two arrays of strings that represent two inclusive events that happened on the same day, `event1` and `event2`, where:
- <code>event1 = [startTime<sub>1</sub>, endTime<sub>1</sub>]</code>
- <code>event2 = [startTime<sub>2</sub>, endTime<sub>2</sub>]</code>

Event times are valid 24 hours format in the form of HH:MM.

A *conflict* happens when two events have some non-empty intersection (i.e., some moment is common to both events).

Return `true` if there is a conflict between two events. Otherwise, return `false`.

### Example 1

#### Input
`event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]`

#### Output
`true`

#### Explanation
The two events intersect at time 2:00 ( :clock2: ).

### Example 2

#### Input
`event1 = ["01:00","02:00"], event2 = ["01:30","03:00"]`

#### Output
`true`

#### Explanation
The two events intersect starting from 1:30 ( :clock130: ) to 2:00 ( :clock2: ).

### Example 3

#### Input
`event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]`

#### Output
`false`

#### Explanation
The two events do not intersect.

### Constraints
- `event1.length == event2.length == 2`
- `event1[i].length == event2[i].length == 5`
- <code>startTime<sub>1</sub> <= endTime<sub>1</sub></code>
- <code>startTime<sub>2</sub> <= endTime<sub>2</sub></code>
- All the event times follow the HH:MM format.

### Solution

```kotlin
fun haveConflict(event1: Array<String>, event2: Array<String>): Boolean {
    val timeToMinutes: (String) -> Int =
        { timeString ->
            timeString
            .split(":")
            .map { it.toInt() }
            .let { (hour, minute) -> (hour * 60) + minute }
        }

    val (startMinute1: Int, endMinute1: Int) = event1.map(timeToMinutes)
    val (startMinute2: Int, endMinute2: Int) = event2.map(timeToMinutes)

    val event1Minutes: IntRange = startMinute1..endMinute1
    val event2Minutes: IntRange = startMinute2..endMinute2

    // The last boolean is necessary for when event 2 begins after and ends before event 1.
    return startMinute1 in event2Minutes || endMinute1 in event2Minutes || startMinute2 in event1Minutes
}
```