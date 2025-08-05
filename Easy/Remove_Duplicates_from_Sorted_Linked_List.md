## Remove Duplicates from Sorted Linked List
### Difficulty: Easy
### [Link](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)

### Description

Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

### Example

#### Input
The head of the linked list `1 -> 1 -> 2 -> 3 -> 3`.

#### Output
The head of the linked list `1 -> 2 -> 3`.

### Constraints

- The number of nodes in the list is in the range [0, 300].
- `-100 <= Node.val <= 100`
- The list is guaranteed to be sorted in ascending order.

### Definition for a Singly-Linked List

```kotlin
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
```

### Solution

```kotlin
fun deleteDuplicates(head: ListNode?): ListNode? {
    if (head == null) return head
    
    var firstNodeForANumber: ListNode = head
    var curNode: ListNode? = head.next
    
    while (curNode != null) {
        if (curNode.`val` != firstNodeForANumber.`val`) {
            firstNodeForANumber.next = curNode
            firstNodeForANumber = curNode
        }
        curNode = curNode.next
    }
    
    firstNodeForANumber.next = null
    return head
}
```