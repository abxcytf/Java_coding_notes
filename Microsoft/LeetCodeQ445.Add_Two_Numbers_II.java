/*
445. Add Two Numbers II
You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

add from most right to left!!!!
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.offerLast(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.offerLast(l2.val);
            l2 = l2.next;
        }
        
        ListNode temp = new ListNode(0);
        int sum = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            sum = temp.val;
            if (!stack1.isEmpty()) {
                sum += stack1.pollLast();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pollLast();
            }
            temp.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = temp;
            temp = head;
            // sum /= 10;
        }
        //有可能没有进制
        return temp.val == 0 ? temp.next : temp;
    }
}
