/*
Follow Up I - Subsection of complete path from root to leaf
*/


public class Solution {
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
        if (root.left == null && root.right == null) {
            updatedResult(sum, path, result);
        } else {
            dfsHelper(root.left, sum, result, path);
            dfsHelper(root.right, sum, result, path);
        }
        path.remove(path.size() - 1);
    }
    
    private void updatedResult(int target, List<Integer> path, List<List<Integer>> result) {
        List<Integer> temp = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            temp.add(path.get(i));
            target -= path.get(i);
            if (target == 0) {
                result.add(new ArrayList<>(temp));
            }
        }
    }
}
