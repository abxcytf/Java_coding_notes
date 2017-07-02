/*
513. Find Bottom Left Tree Value

Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.
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
    //DFS, preorder traverse and update the depth and the most bottom left value while traversal
    //O(logn)
    private int bottomLeftValue = -1;
    private int maxDepth = 0;
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        dfsHelper(root, 1);
        return bottomLeftValue;
    }
    
    private void dfsHelper(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        
        if (depth > maxDepth) {
            //update the result;
            maxDepth = depth;
            bottomLeftValue = root.val;
        }
        dfsHelper(root.left, depth + 1);
        dfsHelper(root.right, depth + 1);
    }
    
    /*****************************************************************************/
    //BFS, time complexity O(n), n is the number of the nodes
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int result = -1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.pollFirst();
                if (i == 0) {
                    result = current.val;
                }
                if (current.left != null) {
                    queue.offerLast(current.left);
                }
                if (current.right != null) {
                    queue.offerLast(current.right);
                }
            }
        }
        return result;
    }
    
    /****************************************************************************/
    //optimized solution, BFS, scan from right to left
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        TreeNode current = null;
        while (!queue.isEmpty()) {
            current = queue.pollFirst();
            if (current.right != null) {
                queue.offerLast(current.right);
            }
            if (current.left != null) {
                queue.offerLast(current.left);
            }
        }
        return current.val;
    }
}
