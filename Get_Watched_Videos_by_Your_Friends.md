## Get Watched Videos by Your Friends :movie_camera: :arrow_forward:
### Difficulty: Medium
### [Link](https://leetcode.com/problems/get-watched-videos-by-your-friends/)

### Description

There are `n` people, each person has a unique id between `0` and `n - 1`. Given the arrays `watchedVideos` and `friends`, where `watchedVideos[i]` and `friends[i]` contain the list of watched videos and the list of friends respectively for the person with `id = i`.

Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, the level $k$ of videos are all watched videos by people with the shortest path exactly equal to $k$ with you. Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency, order them alphabetically from least to greatest.

### Examples

Some input for both examples is:
```
id = 0
watchedVideos = [["A","B"],["C"],["B","C"],["D"]]
friends = [[1,2],[0,3],[0,3],[1,2]]
```
`level` is unique for each example.

Here's a graph to give a visual representation of `friends`:
```
  0
 / \
1   2
 \ /
  3
```

### Example 1

#### Input
`level = 1`

#### Output
`["B","C"] `

#### Explanation

You have `id = 0` (top node in the graph) and your friends (middle 2 nodes in the graph) are:
- Person with `id = 1` -> `watchedVideos = ["C"]`
- Person with `id = 2` -> `watchedVideos = ["B","C"]`

The frequencies of `watchedVideos` by your friends are: 
- B -> 1 
- C -> 2

### Example 2

#### Input
`level = 2`

#### Output
`["D"]`

#### Explanation

You have `id = 0` (top node in the graph) and the only friend of your friends is the person with `id = 3` (bottom node in the graph).

### Constraints
- `n == watchedVideos.length == friends.length`
- `2 <= n <= 100`
- `1 <= watchedVideos[i].length <= 100`
- `1 <= watchedVideos[i][j].length <= 8`
- `0 <= friends[i].length < n`
- `0 <= friends[i][j] < n`
- `0 <= id < n`
- `1 <= level < n`
- If `friends[i]` contains `j`, then `friends[j]` contains `i`.

### Solution

```kotlin
fun watchedVideosByFriends(watchedVideos: List<List<String>>, friends: Array<IntArray>, id: Int, level: Int): List<String> {
    /*
    1st, do a breadth-first search (BFS) to find people and the level they're at. The search will end
    when we find all people at the param level.

    Let peopleToCheck be a deque of pairs that's used as a queue for the BFS. Each pair contains a
    person's ID and the level they're at.
    */
    val peopleToCheck =
        ArrayDeque<Pair<Int, Int>>()
        .apply { add(Pair(id, 0)) }

    val peopleFound: MutableSet<Int> = mutableSetOf(id)
    val peopleAtParamLevel = ArrayList<Int>()
    
    while (peopleToCheck.isNotEmpty()) {
        val (person: Int, personLevel: Int) = peopleToCheck.removeFirst()

        val personsFriendsAtNextLevel: List<Int> =
            friends[person].filter { peopleFound.add(it) }
        
        if (personLevel == level - 1) {
            peopleAtParamLevel.addAll(personsFriendsAtNextLevel)
        } else {
            val nextLevel: Int = personLevel + 1
            for (friend: Int in personsFriendsAtNextLevel) {
                peopleToCheck.add(Pair(friend, nextLevel))
            }
        }
    }

    /*
    Next, create the list of videos.

    Let videoWatchCounts be a map where the keys are video names and the values are
    the counts of times those videos were watched by the people in peopleAtParamLevel.
    */
    val videoWatchCounts: Map<String, Int> =
        peopleAtParamLevel
        .flatMap { watchedVideos[it] }
        .groupingBy { it }
        .eachCount()

    val watchCountGroups: Map<Int, List<String>> =
        videoWatchCounts
        .asIterable()
        .groupBy(
            keySelector = { (_, watchCount: Int) -> watchCount },
            valueTransform = { (video: String, _) -> video }
        )

    return watchCountGroups
        .asIterable()
        .sortedBy { (watchCount, _) -> watchCount }
        .flatMap { (_, videos: List<String>) -> videos.sorted() }
}
```