## Max Total Importance of Roads :motorway: :cityscape:
### Difficulty: Medium
### [Link](https://leetcode.com/problems/maximum-total-importance-of-roads/)

### Description

You are given an integer `n` denoting the number of cities in a country. The cities are numbered from `0` to `n - 1`.

You are also given a 2D integer array `roads` where <code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> denotes that there exists a bidirectional road connecting cities <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.

You need to assign each city with an integer value from `1` to `n`, where each value can only be used once. The importance of a road is then defined as the sum of the values of the two cities it connects.

Return the maximum total importance of all roads possible after assigning the values optimally.

### Example

```
   1
  /|\
 / | \
0 --- 2 --- 4
   | /
   |/
   3
```

#### Input
`n = 5, roads = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]`

#### Output
`43`

#### Explanation

Assign the values 2 to city 0, 4 to city 1, 5 to city 2, 3 to city 3, and 1 to city 4.

- The road (0,1) has an importance of $2 + 4 = 6$.
- The road (1,2) has an importance of $4 + 5 = 9$.
- The road (2,3) has an importance of $5 + 3 = 8$.
- The road (0,2) has an importance of $2 + 5 = 7$.
- The road (1,3) has an importance of $4 + 3 = 7$.
- The road (2,4) has an importance of $5 + 1 = 6$.

The total importance of all roads is $6 + 9 + 8 + 7 + 7 + 6 = 43$.

It can be shown that we cannot obtain a greater total importance than 43.

### Constraints
- <code>2 <= n <= 5 * 10<sup>4</sup></code>
- <code>1 <= roads.length <= 5 * 10<sup>4</sup></code>
- `roads[i].length == 2`
- <code>0 <= a<sub>i</sub>, b<sub>i</sub> <= n - 1</code>
- <code>a<sub>i</sub> != b<sub>i</sub></code>
- There are no duplicate roads.

### Solution

Assign values to cities based on how many neighbors they have. The city with the most neighbors will get the highest value and the city with the least neighbors will get the lowest value.

```kotlin
fun maximumImportance(n: Int, roads: Array<IntArray>): Long {
    val cityNeighborCounts = IntArray(size = n)
    
    for (road: IntArray in roads) {
        for (city: Int in road) {
            cityNeighborCounts[city]++
        }
    }
    
    /*
    Let cityValues be a map where the keys are city numbers
    and the values are the values we assign to those cities.
    */
    val cityValues: Map<Int, Int> =
        cityNeighborCounts
        .withIndex()
        .sortedBy { (_, neighborCount: Int) -> neighborCount }
        .map { (city: Int, _) -> city }
        .withIndex()
        .associate { (indexAfterSorting: Int, city) -> city to indexAfterSorting + 1 }

    /*
    Convert the result of the inner sumOf to a long so the outer sumOf returns a long,
    which is what this function is supposed to return. It seems safe to assume that there
    are some test cases where the max total importance is greater than the int max value.
    */
    return roads.sumOf { road: IntArray ->
        road.sumOf { city: Int -> cityValues.getValue(city) }.toLong()
    }
}
```