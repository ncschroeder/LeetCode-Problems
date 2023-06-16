/*
https://leetcode.com/problems/find-mode-in-binary-search-tree/

Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:
- The left subtree of a node contains only nodes with keys less than or equal to the node's key.
- The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
- Both the left and right subtrees must also be binary search trees.

Definition for a binary tree node.
class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
*/

fun findMode(root: TreeNode?): IntArray {
    // Let valueCounts be a map where the keys are the tree node values and the values are the counts for 
    // how many times that value appears in the tree
    val valueCounts = mutableMapOf<Int, Int>()

    fun incrementCountAndTraverse(node: TreeNode) {
        valueCounts[node.value] = (valueCounts[node.value] ?: 0) + 1
        node.left?.let(::incrementCountAndTraverse)
        node.right?.let(::incrementCountAndTraverse)
    }

    incrementCountAndTraverse(root!!)

    val maxCount: Int = valueCounts.values.max()
    return valueCounts.asSequence()
        .filter { ( _, count ) -> count == maxCount }
        .map { ( value, _ ) -> value }
        .toList()
        .toIntArray()
}
