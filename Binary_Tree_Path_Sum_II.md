## Binary Tree Path Sum II :heavy_plus_sign:
### Difficulty: Medium
### [Link](https://leetcode.com/problems/path-sum-ii/)

The LeetCode name for this problem is just "Path Sum II" but I decided to prefix it with "Binary Tree" to make it clear that this problem involves paths in a binary tree and not a graph or something else. This problem is similar to the problems ["Path Sum"](https://leetcode.com/problems/path-sum/) and ["Path Sum III"](https://leetcode.com/problems/path-sum-iii/), which I solved but didn't include in this repo.

### Description

Given the root of a binary tree and an integer `targetSum`, return all root-to-leaf paths where the sum of the node values in the path equals `targetSum`. Each path should be returned as a list of the node values, not node references.

A *root-to-leaf path* is a path starting from the root and ending at any leaf node. A *leaf* is a node with no children.

### Example

#### Input

```
root = the root of this tree:

        5
       / \
      /   \
     4     8
    /     / \
   /     /   \
  11    13    4
  / \        / \
 /   \      /   \
7     2    5     1

targetSum = 22
```

#### Output
`[[5,4,11,2],[5,8,4,5]]`

#### Explanation
The sums of the values of those paths are 22.

### Constraints
- The number of nodes in the tree is in the range [0, 5000].
- `-1000 <= Node.val <= 1000`
- `-1000 <= targetSum <= 1000`

### Definition for a Binary Tree Node

```kotlin
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
```

### Solution

```kotlin
fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
    val paths = ArrayList<List<Int>>()
    
    // Used as a stack for keeping track of a path that's being traversed.
    val curPath = ArrayList<Int>()
    
    fun traverse(node: TreeNode?) {
        if (node == null) return
        curPath.add(node.`val`)
        
        if (node.left == null && node.right == null) {
            if (curPath.sum() == targetSum) {
                paths.add(curPath.toList())
            }
        } else {
            traverse(node.left)
            traverse(node.right)
        }
        
        // Backtrack
        curPath.removeLast()
    }
    
    traverse(root)
    return paths
}
```