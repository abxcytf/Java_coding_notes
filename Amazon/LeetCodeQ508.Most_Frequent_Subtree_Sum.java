/*
508. Most Frequent Subtree Sum

Given the root of a tree, you are asked to find the most frequent subtree sum. 
The subtree sum of a node is defined as the sum of all the node values formed 
by the subtree rooted at that node (including the node itself). 
So what is the most frequent subtree sum value? If there is a tie, 
return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, 
return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is 
      in the range of 32-bit signed integer.
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
    
    private Map<Integer, Integer> sumToCount;
    private int maxCount;
    public int[] findFrequentTreeSum(TreeNode root) {
        maxCount = 0;
        sumToCount = new HashMap<Integer, Integer>();
        
        postorderTraverse(root);
        
        List<Integer> temp = new ArrayList<>();
        for (int key : sumToCount.keySet()) {
            if (sumToCount.get(key) == maxCount) {
                temp.add(key);
            }
        }
        
        int[] result = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }
        return result;
    }
    
    private int postorderTraverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = postorderTraverse(root.left);
        int right = postorderTraverse(root.right);
        int sum = left + right + root.val;
        int count = sumToCount.getOrDefault(sum, 0) + 1;
        sumToCount.put(sum, count);
        
        maxCount = Math.max(maxCount, count);
        return sum;
    }
}
