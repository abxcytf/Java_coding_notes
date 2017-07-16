/*
538. Convert BST to Greater Tree

Given a Binary Search Tree (BST), convert it to a Greater Tree such that 
every key of the original BST is changed to the original key plus sum of 
all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
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
    //recursion, and right-mid-left traverse
    private int sum = 0; //help to cumulate the sum
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
       traverseAndConvert(root); //right, current, left
        return root;
    }
    
    private void traverseAndConvert(TreeNode root) {
        if (root == null) {
            return;
        }
        traverseAndConvert(root.right);
        root.val += sum;
        sum = root.val;
        traverseAndConvert(root.left);
    }
}
