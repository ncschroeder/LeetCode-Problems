### Redundant Connection
#### Difficulty: Medium
#### [Link](https://leetcode.com/problems/redundant-connection/)

### Description

In this problem, a *tree* is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with `n` nodes labeled from `1` to `n`, with one additional edge added. The added edge has two different vertices chosen from `1` to `n`, and was not an edge that already existed. The graph is represented as an array `edges` of length `n` where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the graph.

Return an edge that can be removed so that the resulting graph is a tree of `n` nodes. If there are multiple answers, return the answer that occurs last in the input.

### Example 1

```
1 --- 2
 \   /
  \ /
   3
```

#### Input
`edges = [[1,2],[1,3],[2,3]]`

#### Output
`[2,3]`

### Example 2

```
2 -- 1 -- 5
|    |
3 -- 4
```

#### Input
`edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]`

#### Output
`[1,4]`

### Constraints

- `n == edges.length`
- `3 <= n <= 1000`
- `edges[i].length == 2`
- <code>1 <= a<sub>i</sub> < b<sub>i</sub> <= edges.length</code>
- <code>a<sub>i</sub> != b<sub>i</sub></code>
- There are no repeated edges.
- The given graph is connected.

### Solutions

#### My 2<sup>nd</sup> and More Performant Solution

This solution does a depth-first search (DFS) with backtracking to find the cycle in the graph. Then, it finds the last edge where both nodes are in the cycle.

```kotlin
fun findRedundantConnection(edges: Array<IntArray>): IntArray {        
    val numNodes: Int = edges.size
    val neighborsMap: Map<Int, MutableList<Int>> =
        (1..numNodes).associateWith { ArrayList() }
    
    for ((node1: Int, node2: Int) in edges) {
        neighborsMap.getValue(node1).add(node2)
        neighborsMap.getValue(node2).add(node1)
    }
    
    /*
    A linked hash set is used since the iteration order is the same as the insertion order.
    This is necessary for getting the nodes in the cycle when calling the dropWhile function below.
    */
    val nodesInCurPath = LinkedHashSet<Int>()
    
    /*
    This does a DFS with backtracking to find the cycle. When the cycle is found, a set of the nodes
    in the cycle will be returned. The first call to this function and some recursive calls will return
    this set. If a dead end is reached, null will be returned. This can happen for some recursive calls.
    */
    fun getNodesInCycle(node: Int, prevNode: Int? = null): Set<Int>? {
        if (!nodesInCurPath.add(node)) {
            /*
            The cycle has been found and includes the param node and
            all nodes that were added to nodesInCurPath after it.
            */
            return nodesInCurPath.dropWhile { it != node }.toHashSet()
        }
        
        val neighborsToSearch: List<Int> =
            neighborsMap
            .getValue(node)
            .let { if (prevNode != null) it - prevNode else it }
        
        neighborsToSearch
        .firstNotNullOfOrNull { getNodesInCycle(node = it, prevNode = node) }
        ?.let { return it }
        
        nodesInCurPath.remove(node)
        return null
    }
    
    val nodesInCycle: Set<Int> = getNodesInCycle(node = 1)!!
    return edges.last { e: IntArray -> e.all { it in nodesInCycle } }
}
```

#### My 1<sup>st</sup> Solution

This solution iterates backwards through the edges and for each edge, a DFS is done to see if a tree can be formed if that edge is ignored.

```kotlin
fun findRedundantConnection(edges: Array<IntArray>): IntArray {
    val numNodes: Int = edges.size
    val neighborsMap: Map<Int, MutableList<Int>> =
        (1..numNodes).associateWith { ArrayList() }
    
    for ((node1: Int, node2: Int) in edges) {
        neighborsMap.getValue(node1).add(node2)
        neighborsMap.getValue(node2).add(node1)
    }
    
    fun canFormTreeIfRemoved(edge: IntArray): Boolean {            
        val visitedNodes = HashSet<Int>()
        
        /*
        This does a DFS on the component of the graph that the param node is in. The param edge won't
        be used. If a cycle is found, true will be returned. Otherwise, false will be returned.
        */
        fun searchForCycle(node: Int, prevNode: Int? = null): Boolean {
            if (!visitedNodes.add(node)) return true
            
            val neighborsToIgnore: List<Int> =
                buildList(capacity = 2) {
                    if (prevNode != null) add(prevNode)
                    
                    if (node in edge) {
                        add(edge.single { it != node })
                    }
                }
            
            val neighborsToSearch: List<Int> = neighborsMap.getValue(node) - neighborsToIgnore
            return neighborsToSearch.any { searchForCycle(node = it, prevNode = node) }
        }
        
        /*
        Since we won't be using the param edge when doing the DFS, that might cause us to
        not be able to visit all nodes. If we aren't able to visit all nodes, then that
        means the graph wouldn't be connected if we removed the param edge, which means it
        wouldn't be a tree. Check that a cycle doesn't get found and all nodes get visited.
        */
        return !searchForCycle(node = 1) && visitedNodes.size == numNodes
    }
    
    return edges.last(::canFormTreeIfRemoved)
}
```