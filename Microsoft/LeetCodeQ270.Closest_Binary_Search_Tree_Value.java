/*
270. Closest Binary Search Tree Value

Given a non-empty binary search tree and a target value, 
find the value in the BST that is closest to the target.

Note:
. Given target value is a floating point.
. You are guaranteed to have only one unique value in the BST that is closest to the target.
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
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        
        //initial value of the potential result is the root value
        int result = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - result)) {
                //update the result value when this check hits
                result = root.val;
            }
            
            //decide which side to go, if root.val < target value : go right to find the bigger value
            // if root.val > target vlaue : go left to find the small value
            root = root.val < target ? root.right : root.left;
        }
        
        return result;
    }
}
