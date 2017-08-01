/*
Follow Up IV: Path -> from leaf node to other leaf node
*/

public class Solution {
    //time complexity: O(n), space complexity O(1)
    public int maxPathSum(TreeNode root, int sum) {
        int[] max = {Integer.MIN_VALUE};
        dfsHelper(root, max);
        return max[0];
    }
    
    private int dfsHelper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = dfsHelper(root.left, max);
        int right = dfsHelper(root.right, max);
        
        left = Math.max(left, 0);
        right = Math.max(right, 0);
        
        //update global max only for root.left and root.right not null
        if (root.left != null && root.right != null) {
            max[0] = Math.max(max[0], left + right + root.val);    
        }
        
        //report back up -> check null first
        if (root.left == null) {
            return root.val + right;
        } else if (root.right == null) {
            return root.val + left;
        } else {
            return Math.max(left, right) + root.val; //return the larger side    
        }
    }
}
