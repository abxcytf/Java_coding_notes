/*
117. Populating Next Right Pointers in Each Node II
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode head = null; //head of the next level;
        TreeLinkNode prev = null; //the leading node on the next level
        TreeLinkNode current = root; //current node of current level
        
        while (current != null) {
            while (current != null) {
                //left child
                if (current.left != null) {
                    if (prev != null) {
                        //connect the right node
                        prev.next = current.left;
                    } else {
                        head = current.left;
                    }
                    //update prev
                    prev = current.left;
                }
                
                //right child
                if (current.right != null) {
                    if (prev != null) {
                        //connect the right node
                        prev.next = current.right;
                    } else {
                        head = current.right;
                    }
                    //update prev
                    prev = current.right;
                }
                
                //move to next node;
                current = current.next;
            }
            //move to next level
            //update current to next level head
            current = head;
            head = null;
            prev = null;
        }
    }
}
