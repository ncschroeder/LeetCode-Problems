/*
https://leetcode.com/problems/determine-if-two-events-have-conflict/

You are given two arrays of strings that represent two inclusive events that happened on the same day, event1 and event2, where:
- event1 = [startTime1, endTime1]
- event2 = [startTime2, endTime2]

Event times are valid 24 hours format in the form of HH:MM.

A conflict happens when two events have some non-empty intersection (i.e., some moment is common to both events).

Return true if there is a conflict between two events. Otherwise, return false.
*/

/*
To solve this, we can convert the times to minutes and then 1st check if either the event 1 start minute or
end minute is in the range of minutes that event 2 takes place in. The situation that this won't cover is when
event 2 begins after and ends before event 1. To handle that situation, we can check if the event 2 start
minute is in the range of minutes that event 1 is in.
*/
fun haveConflict(event1: Array<String>, event2: Array<String>): Boolean {
    fun timeToMinutes(timeString: String): Int {
        val ( hourString, minuteString ) = timeString.split(":")
        return (hourString.toInt() * 60) + minuteString.toInt()
    }

    val ( startMinute1, endMinute1 ) = event1.map(::timeToMinutes)
    val ( startMinute2, endMinute2 ) = event2.map(::timeToMinutes)
    val event2Minutes: IntRange = startMinute2..endMinute2

    return startMinute1 in event2Minutes || endMinute1 in event2Minutes ||
        startMinute2 in startMinute1..endMinute1
}