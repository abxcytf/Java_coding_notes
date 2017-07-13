/*
617. Merge Two Binary Trees

Given two binary trees and imagine that when you put one of them to cover the 
other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if 
two nodes overlap, then sum node values up as the new value of the merged node. 
Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
Note: The merging process must start from the root nodes of both trees.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //iteration using two stack
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        
        //BFS
        stack1.offerLast(t1);
        stack2.offerLast(t2);
        
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode current1 = stack1.pollLast();
            TreeNode current2 = stack2.pollLast();
            TreeNode node = current1;
            current1.val += current2.val;
            
            if (current1.left != null && current2.left != null) {
                stack1.offerLast(current1.left);
                stack2.offerLast(current2.left);
            } else if (current1.left == null && current2.left == null) {
                node.left = null;
            } else {
                node.left = current1.left == null ? current2.left : current1.left;
            }
            
            if (current1.right != null && current2.right != null) {
                stack1.offerLast(current1.right);
                stack2.offerLast(current2.right);
            } else if (current1.right == null && current2.right == null) {
                node.right = null;
            } else {
                node.right = current1.right == null ? current2.right : current1.right;
            }
        }
        return t1;
    }
    
    /************************************************************************************/
    //recursion
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null; 
        } else if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        
        TreeNode newNode = new TreeNode(t1.val + t2.val);
        newNode.left = mergeTrees(t1.left, t2.left);
        newNode.right = mergeTrees(t1.right, t2.right);
        return newNode;
    }
    
    /***************************************************************************************/
    //more elegant recursion solution
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null; 
        } else if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
