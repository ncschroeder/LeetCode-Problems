/*
https://leetcode.com/problems/find-the-town-judge/

In a town, there are `n` people labeled from 1 to `n`. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:
- The town judge trusts nobody.
- Everybody (except for the town judge) trusts the town judge.
- There is exactly one person that satisfies properties 1 and 2.
- You are given an array `trust` where `trust[i]` = `[a<sub>i</sub>, b<sub>i</sub>]` representing that the person labeled `a<sub>i</sub>` trusts the person labeled `b<sub>i</sub>`. If a trust relationship does not exist in `trust` array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

Constraints:
- 1 <= `n` <= 1000
- 0 <= `trust.length` <= 10^4
- `trust[i].length` == 2
- All the pairs of `trust` are unique.
- `a<sub>i</sub> != b<sub>i</sub>`
- 1 <= `a<sub>i</sub>`, `b<sub>i</sub>` <= n
*/

fun findJudge(n: Int, trust: Array<IntArray>): Int {
    // Find the people that don't trust anybody, or the non-trusters. This can be done by getting a Set of all
    // the people that do trust somebody and then finding the people in the town that aren't in that Set.
    val trusters: Set<Int> =
        trust.mapTo(HashSet()) { (truster: Int, _) -> truster }

    // Let nonTrustersMap be a Map where the keys are the IDs of the non-trusters, and the values are the counts of how many people trust that person
    val nonTrustersMap: MutableMap<Int, Int> = 
        (1..n)
        .minus(trusters)
        .associateWithTo(HashMap(), valueSelector = { 0 })
        
    for ((_, trustee: Int) in trust) {
        nonTrustersMap[trustee]?.let { nonTrustersMap[trustee] = it + 1 }
    }

    // Find the non-truster that is trusted by all people besides themself
    return nonTrustersMap.asIterable()
        .firstOrNull { (_, numTrusters: Int) -> numTrusters == n - 1 }
        ?.let { (judge: Int, _) -> judge }
        ?: -1
}