/*
83. Remove Duplicates from Sorted List
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode current = head;
        while (current != null) {
			if (current.next != null && current.val == current.next.val) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
		return head;
    }
}
