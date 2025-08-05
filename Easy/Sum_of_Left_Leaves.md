## Sum of Left Leaves :heavy_plus_sign: :arrow_left: :maple_leaf:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/sum-of-left-leaves/)

### Description

Given the root of a binary tree, return the sum of all left leaves.

A *leaf* is a node with no children. A *left leaf* is a leaf that is the left child of another node.

### Example

#### Input

```
root = the root of this tree:

   3
  / \
 /   \
9     20
      / \
     /   \
    15    7
```

#### Output
`24`

#### Explanation

9 and 15 are the only left leaves.

### Constraints

- The number of nodes in the tree is in the range [1, 1000].
- `-1000 <= Node.val <= 1000`

### Definition for a Binary Tree Node

```kotlin
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
```

### My Favorite Solution

```kotlin
fun sumOfLeftLeaves(root: TreeNode?): Int =
    if (root == null) 0
    else {
        val leftSum: Int =
            root.left?.let {
                if (it.left == null && it.right == null) it.`val`
                else sumOfLeftLeaves(it) 
            } ?: 0
        
        leftSum + sumOfLeftLeaves(root.right)
    }
```

### My 1<sup>st</sup> Solution

```kotlin
fun sumOfLeftLeaves(root: TreeNode?): Int {
    if (root == null) return 0
    
    val leftChild: TreeNode? = root.left
    val leftSum: Int =
        when {
            leftChild == null -> 0
            
            leftChild.left == null && leftChild.right == null -> leftChild.`val`
            
            else -> sumOfLeftLeaves(leftChild)
        }
    
    return leftSum + sumOfLeftLeaves(root.right)
}
```