## Linked List Cycle :recycle:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/linked-list-cycle/)

### Description

Given `head`, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that tail's next pointer is connected to. Note that `pos` is not passed as a parameter.

Return `true` if there is a cycle in the linked list. Otherwise, return `false`.

### Constraints
- The number of the nodes in the list is in the range [0, 10<sup>4</sup>].
- <code>10<sup>5</sup> <= Node.val <= 10<sup>5</sup></code>
- `pos` is `-1` or a valid index in the list.

### Follow-Up
Can you solve it using $O(1)$ (i.e. constant) memory? Yes.

### C++ Solution that Uses Pointers :point_up_2:

As mentioned in the README, I came up with an algorithm that needs to know the memory addresses of objects, and those can be accessed with pointers in C++ and cannot be accessed in Kotlin.

This solution has a time and space complexity of $O(n)$.

Definition for a singly-linked list:
```c++
struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x) : val(x), next(NULL) {}
};
```

```c++
bool hasCycle(ListNode* head) {
    std::unordered_set<ListNode*> nodePtrs;
    ListNode* curNode = head;
    
    while (curNode != nullptr) {
        /*
        insert returns a pair and the 2nd item of that pair is a bool that is true if the item wasn't in the set and got inserted.
        That bool is false if the item was already in the set and didn't get inserted.
        */
        if (!nodePtrs.insert(curNode).second) return true;
        curNode = curNode->next;
    }
    
    return false;
}
```

### Kotlin Solution that Doesn't Use Pointers

This solution has a time complexity of $O(n^2)$ and a space complexity of $O(1)$.

Definition for a singly-linked list:
```kotlin
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
```

```kotlin
fun hasCycle(head: ListNode?): Boolean {
    var curNode: ListNode? = head?.next
    var curNodeIndex = 1
    
    while (curNode != null) {
        /*
        Check if there are any nodes before curNode that are referentially equal to curNode. If there are then that
        means that curNode is on its 2nd loop of a cycle.
        
        Non-null assertions will be done on nodes before curNode or equal to curNode.
        */

        var nodeToCheck: ListNode = head!!
        
        for (i: Int in 0 until curNodeIndex) {
            if (nodeToCheck === curNode) return true
            nodeToCheck = nodeToCheck.next!!
        }
        
        curNode = curNode.next
        curNodeIndex++
    }
    
    return false
}
```