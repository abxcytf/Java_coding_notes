/*
23. Merge k Sorted Lists
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (a, b) -> (a.val - b.val));
        
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            minHeap.offer(lists[i]);
        }
        if (minHeap.isEmpty()) {
            return null;
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            if (node.next != null) {
                minHeap.offer(node.next);
            }
            node.next = null;
            current.next = node;
            current = current.next;
        }
        return dummy.next;
    }
}
