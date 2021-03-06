/*
Follow Up III: Path -> Any node to any other node
*/

public class Solution {
    //time complexity: O(n), space complexity O(1)
    public int maxPathSum(TreeNode root) {
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
        //update the global max
        max[0] = Math.max(max[0], left + right + root.val);   //the only difference from follow up II
        return Math.max(left, right) + root.val; //return the larger side
    }
}
