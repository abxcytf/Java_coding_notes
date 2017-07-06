/*
98. Validate Binary Search Tree
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
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
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean helper(TreeNode root, long min, long max) {
        //base case
        if (root == null) {
            return true;
        }
        //current level
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
    
    /******************************************************************************************/
    //using ResultType approach
    class ResultType {
        boolean is_bst;
        int maxValue, minValue;
        ResultType(boolean is_bst, int maxValue, int minValue){
            this.is_bst = is_bst;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }
    
    public boolean isValidBST(TreeNode root) {
        return isValid(root).is_bst;
    }
    
    private ResultType isValid(TreeNode root) {
        if (root == null) {
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        ResultType left = isValid(root.left);
        ResultType right = isValid(root.right);
        
        if (!left.is_bst || !right.is_bst) return new ResultType(false, 0, 0);
        if (root.left != null && root.val <= left.maxValue || root.right != null && root.val >= right.minValue) 
            return new ResultType(false, 0, 0);
            
        return new ResultType(true, Math.max(root.val, right.maxValue), Math.min(root.val, left.minValue));
    }
}
