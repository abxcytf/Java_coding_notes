/*
给定一棵二叉树的根节点root，按照如下两种标准分别实现二叉树边界节点的逆时针打印。
标准一：解答在这页面的code
  1. 头节点为边界节点。
  2. 叶节点为边界节点。
  3. 如果节点在其所在层中是最左或者最右的，那么也是边界节点。
标准二：（https://github.com/abxcytf/Java_coding_notes/blob/master/Amazon/LeetCodeQ545.Boundary_of_Binary_Tree.java）
  1. 头节点为边界节点。
  2. 叶节点为边界节点。
  3. 树左边界延伸下去的路径为边界节点。
  4. 树有边界延伸下去的路径为边界节点。
*/

public class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> left = new LinkedList<>();
        List<Integer> leaves = new LinkedList<>();
        List<Integer> right = new LinkedList<>();
        
        Deque<TreeNode> queue = new ArrayDeque<>();
    
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.pollFirst();
                if (i == 0) {
                    left.add(current.val);
                }
                if (i != 0 && i != size - 1 && current.left == null && current.right == null) {
                    leaves.add(current.val);
                }
                if (i != 0 && i == size - 1) {
                    right.add(0,current.val);
                }
                if (current.left != null) {
                    queue.offerLast(current.left);
                }
                if (current.right != null) {
                    queue.offerLast(current.right);
                }
            }
        }
        
        result.addAll(left);
        result.addAll(leaves);
        result.addAll(right);

        return result;
    }
}
