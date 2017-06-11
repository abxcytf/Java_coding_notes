/*
138. Copy List with Random Pointer
A linked list is given such that each node contains an additional 
random pointer which could point to any node in the list or null.

Return a deep copy of the list.8. Copy List with Random Pointer
*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    //use the hashmap to store the mapping relation, cause extra space O(n)
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while(cur != null) {
            //1. create nodes of cur, next, random
            //check null & map first
            if(!map.containsKey(cur)) {
                RandomListNode curCopy = new RandomListNode(cur.label);
                map.put(cur, curCopy);
            }
            if (cur.next != null && !map.containsKey(cur.next)) {
                RandomListNode nextCopy = new RandomListNode(cur.next.label);
                map.put(cur.next, nextCopy);
            }
            if (cur.random != null && !map.containsKey(cur.random)) {
                RandomListNode randomCopy = new RandomListNode(cur.random.label);
                map.put(cur.random, randomCopy);
            }
            //2. connect curCopy with nextCopy, randomCopy
            //check null first
            if (cur.next != null) {
                map.get(cur).next = map.get(cur.next);
            }
            if (cur.random != null) {
                 map.get(cur).random = map.get(cur.random);
            }
            //3.next round
            cur = cur.next;
        }
        return map.get(head);
    }
    
    
    /********************************************************************************************************/
    //if we can change the input, we can save the extra space to solve this question
    //optimized solution, time complexity O(n), space complexity O(1)
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        //1th pass: copy next;
        copyNext(head);
        //2nd pass: copy random
        copyRandom(head);
        //3rd pass: Split linkedlist
        return splitList(head);
    }
    //copy next
    private void copyNext(RandomListNode head) {
        while (head != null) {
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.random = head.random;
            newNode.next = head.next;
            head.next = newNode;
            head = head.next.next;
        }
    }
    //copy random
    private void copyRandom(RandomListNode head) {
        while (head != null) {
            if (head.next.random != null) {
                head.next.random = head.random.next; //copied node.next
            }
            head = head.next.next;
        }
    }
    //split
    private RandomListNode splitList(RandomListNode head) {
        RandomListNode newHead = head.next;
        while (head != null) {
            RandomListNode temp = head.next;
            head.next = temp.next;
            if (temp.next != null) {
                temp.next = temp.next.next;
            }
            head = head.next;
        }
        return newHead;
    }
}
