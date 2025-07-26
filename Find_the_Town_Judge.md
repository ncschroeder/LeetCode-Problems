## Find the Town Judge :man_judge: :woman_judge:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/find-the-town-judge/)

I included this in the "Graphs" problems list. However, a graph didn't come to mind when I solved this problem. It was many months later when I was browsing LeetCode problems with the tag "Graph" when I saw that this problem has that as a tag. Then I realized that you could think of the people in the town as nodes in a graph and trust relationships as edges.

### Description

In a town, there are `n` people labeled from `1` to `n`. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:
1. The town judge trusts nobody.
2. Everybody (except for the town judge) trusts the town judge.
3. There is exactly one person that satisfies properties 1 and 2.

You are given `n` and an array `trust` where <code>trust[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> representing that the person labeled <code>a<sub>i</sub></code> trusts the person labeled <code>b<sub>i</sub></code>. If a trust relationship does not exist in the `trust` array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return `-1` otherwise.

### Example 1

#### Input
`n = 3, trust = [[1,3],[2,3]]`

### Output
`3`

### Explanation

Person 1 and person 2 trust person 3 but person 3 doesn't trust anybody.

### Example 2

#### Input
`n = 3, trust = [[1,3],[2,3],[3,1]]`

### Output
`-1`

### Explanation

Everybody trusts somebody.

### Constraints
- `1 <= n <= 1000`
- <code>0 <= trust.length <= 10<sup>4</sup></code>
- `trust[i].length == 2`
- All the pairs of `trust` are unique.
- <code>a<sub>i</sub> != b<sub>i</sub></code>
- <code>1 <= a<sub>i</sub>, b<sub>i</sub> <= n</code>

### Solution

```kotlin
fun findJudge(n: Int, trust: Array<IntArray>): Int {
    /*
	First, find the people that don't trust anybody, or the non-trusters.
	This can be done by getting a set of all the people that do trust
	somebody and then finding the people that aren't in that set.
	*/
	val trusters: Set<Int> =
		trust.mapTo(HashSet()) { (truster: Int, _) -> truster }

	/*
	Let nonTrustersAndNumTrusters be a map where the keys are the non-trusters
	and the values start at 0 and get set to the numbers of trusters.
	*/
	val nonTrustersAndNumTrusters: MutableMap<Int, Int> =
		((1..n) - trusters).associateWithTo(HashMap()) { 0 }
    
    for ((_, trustee: Int) in trust) {
        nonTrustersAndNumTrusters[trustee]?.let { nonTrustersAndNumTrusters[trustee] = it + 1 }
    }

    // See if there's a non-truster that's trusted by all people besides themself.
    return (
        nonTrustersAndNumTrusters
        .asIterable()
        .firstOrNull { (_, numTrusters: Int) -> numTrusters == n - 1 }
        ?.let { (judge: Int, _) -> judge }
        ?: -1
    )
}
```