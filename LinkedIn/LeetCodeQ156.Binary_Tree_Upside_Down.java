/*
156. Binary Tree Upside Down

Given a binary tree where all the right nodes are either leaf nodes with a 
sibling (a left node that shares the same parent node) or empty, flip it upside 
down and turn it into a tree where the original right nodes turned into left leaf nodes. 
Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
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
    
    //iteration implementation
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        
        TreeNode current = root;
        TreeNode next = null;
        TreeNode prev = null;
        TreeNode temp = null;
        
        while (current != null) {
            next = current.left;
            
            //reconnect nodes now, need temp to keep the previous right child
            current.left = temp;
            //update temp before connect current new right child
            temp = current.right;
            current.right = prev;
            
            prev = current;
            current = next;
        }
        return prev;
    }
    
    /**********************************************************************************************/
    //recursion implementation
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right; //set new left child
        root.left.right = root; //set new right child
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
