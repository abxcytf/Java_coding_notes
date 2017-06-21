/*
160. Intersection of Two Linked Lists

Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

. If the two linked lists have no intersection at all, return null.
. The linked lists must retain their original structure after the function returns.
. You may assume there are no cycles anywhere in the entire linked structure.
. Your code should preferably run in O(n) time and use only O(1) memory.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        //get the tail of list A
        ListNode tailA = headA;
        while (tailA.next != null) {
            tailA = tailA.next;
        }
        
        //connect list A tail to B head to create the cycle
        tailA.next = headB;
        ListNode connect = findListCycleStart(headA);
        tailA.next = null; //set it back
        return connect;
    }
    
    private ListNode findListCycleStart(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (slow != fast) {
            if (fast == null || fast.next == null) { //cycle not existing
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } //slow == fast currently
        
        
        slow = head;
        fast = fast.next;
        
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}
