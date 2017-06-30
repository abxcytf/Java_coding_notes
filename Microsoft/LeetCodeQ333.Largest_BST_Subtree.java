/*
333. Largest BST Subtree

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), 
where largest means subtree with largest number of nodes in it.

Note:
. A subtree must include all of its descendants.
. Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
. The Largest BST Subtree in this case is the highlighted one. 
. The return value is the subtree's size, which is 3.

Follow up:
. Can you figure out ways to solve it with O(n) time complexity?
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
    //brutal force, top-down recursion, time complexity O(n^2)
    public class ResultType {
        public int size;
        public long lower;
        public long upper;
        
        public ResultType(int size, long lower, long upper) {
            this.size = size;
            this.lower = lower;
            this.upper = upper;
        }
    }
    
    public int max = 0;
    
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        preorderTraverse(root);
        return max;
    }
    
    private ResultType preorderTraverse(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MAX_VALUE, Integer.MIN_VALUE); 
        }
        ResultType left = preorderTraverse(root.left);
        ResultType right = preorderTraverse(root.right);
        
        if (left.size == -1 || right.size == -1 || left.upper >= root.val || right.lower <= root.val) {
            return new ResultType(-1, 0, 0);
        }
        
        int size = left.size + 1 + right.size;
        max = Math.max(max, size);
        return new ResultType(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
}
