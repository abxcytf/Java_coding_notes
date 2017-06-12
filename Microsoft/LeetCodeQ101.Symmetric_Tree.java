/*
101. Symmetric Tree
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
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
    
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        if (root.left == null || root.right == null) {
            return root.left == root.right;
        }
        queue.offerLast(root.left);
        queue.offerLast(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollFirst();
            if (left.val != right.val) {
                return false;
            }
            if (left.left == null && right.right != null || left.left != null && right.right == null) {
                return false;
            } else if (left.left != null && right.right != null) {
                queue.offerLast(left.left);
                queue.offerLast(right.right);
            }
            
            if (left.right != null && right.left == null || left.right == null && right.left != null) {
                return false;
            } else if (left.right != null && right.left != null) {
                queue.offerLast(left.right);
                queue.offerLast(right.left);
            }
        }
        return true;
    }
    
    /***************************************************************************************/
    //iterative, using queue
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        //Deque<TreeNode> queue = new LinkedList<>();
        //这里不能用deque因为deque不能放null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return left == right;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
    
    /***************************************************************************/
    //recursion
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode left, TreeNode right) {
        // if (left == null && right == null) {
        //     return true;
        // } else if (left == null && right != null || left != null && right == null) {
        //     return false;
        // }
        if (left == null || right == null) {
            return left == right;
        }
        
        if (left.val != right.val) {
            return false;
        }
        
        return helper(left.right, right.left) && helper(left.left, right.right);
    }
}
