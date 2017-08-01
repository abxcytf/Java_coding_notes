/*
437. Path Sum III
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
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
     // time complexityO(n^2)
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    private int findPath(TreeNode root, int sum) {
        int result = 0;
        if (root == null) return result;
        if (sum == root.val) {
            result++;
        }
        result += findPath(root.left, sum - root.val);
        result += findPath(root.right, sum - root.val);
        return result;
    }
    
    /*****************************************************************/
    //optimized solution
    /*
    So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum),
    and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it does, 
    we added up the ways of prefix sum - target into res.
    */
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); //Default sum = 0, has one count
        return backtrack(root, 0, sum, map);
    }
    
    //Backtrack one pass
    private int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map) {
        if (root == null) return 0;
        sum += root.val;
        int res = map.getOrDefault(sum - target, 0); //see if there is a subtree sum equals to target
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        //extend to left and right child
        res += backtrack(root.left, sum, target, map) + backtrack(root.right, sum, target, map);
        map.put(sum, map.getOrDefault(sum, 0) - 1); //remove the current node so it wont affect other path
        return res;
    }
}
