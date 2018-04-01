/*
237. Delete Node in a Linked List
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.

Show Company Tags
Show Tags
Show Similar Problems
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        } 
        
        //the node is not the tail, so its next node must be valid
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
