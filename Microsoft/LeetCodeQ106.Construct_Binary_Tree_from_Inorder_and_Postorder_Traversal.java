/*
106. Construct Binary Tree from Inorder and Postorder Traversal

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeHelper(0, postorder.length - 1, 0, inorder.length - 1, postorder, inorder);
    }
    
    private TreeNode buildTreeHelper(int postStart, int postEnd, int inStart, int inEnd, int[] postorder, int[] inorder) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }  
        
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inIndex = 0; //try to find the root index in the inorder
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        
        root.left = buildTreeHelper(postStart, postStart + inIndex - 1 - inStart, inStart, inIndex - 1, postorder, inorder);
        root.right = buildTreeHelper(postEnd + inIndex - inEnd, postEnd - 1, inIndex + 1, inEnd, postorder, inorder);
        return root;
    }
}
