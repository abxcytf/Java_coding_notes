/*
113. Path Sum II
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        pathSumHelper(root, sum, result, new ArrayList<>());
        return result;
    }
    
    private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> result, List<Integer> answer) {
        if (root == null) return;
        sum -= root.val;
        answer.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                result.add(new ArrayList<>(answer));
                answer.remove(answer.size() - 1);
                return;
            }
        }
        pathSumHelper(root.left, sum, result, answer);
        pathSumHelper(root.right, sum, result, answer);
        sum += root.val;
        answer.remove(answer.size() - 1);
    }
  
    /**********************************************************************************************************/
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        dfsHelper(root, sum, result, new ArrayList<Integer>());
        
        return result;
    }
    
    private void dfsHelper(TreeNode root, int sum, List<List<Integer>> result, List<Integer> path) {
        if (root == null) {
            return;
        }
        
        path.add(root.val);
        
        if (sum == root.val && root.left == null && root.right == null) {
            //only check at leaf node
            result.add(new ArrayList<>(path));
            //return;
        } else {
            dfsHelper(root.left, sum - root.val, result, path);
            dfsHelper(root.right, sum - root.val, result, path);
        }
        
        path.remove(path.size() - 1);
    }
}
