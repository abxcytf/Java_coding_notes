/*
536. Construct Binary Tree from String

You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, 
one or two pairs of parenthesis. The integer represents the root's value and a pair 
of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
Note:
. There will only be '(', ')', '-' and '0' ~ '9' in the input string.
. An empty tree is represented by "" instead of "()".
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
    /************************************************************************************************/
    //recursion implementation
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int firstParenIndex = s.indexOf("(");
        int value = firstParenIndex == -1 ? 
          Integer.parseInt(s) : Integer.parseInt(s.substring(0, firstParenIndex));
        TreeNode current = new TreeNode(value);
        //leaf
        if (firstParenIndex == -1) {
            return current;
        }
        int start = firstParenIndex;
        int leftParenCount = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftParenCount++;
            } else if (s.charAt(i) == ')') {
                leftParenCount--;
            }
            //always start to construct the left child node of the parent first if it exists
            if (leftParenCount == 0 && start == firstParenIndex) {
                current.left = str2tree(s.substring(start + 1, i));
                start = i + 1;
            } else if (leftParenCount == 0) {
                current.right = str2tree(s.substring(start + 1, i));
            }
        }
        return current;
    }
       
    /**************************************************************************************************/
    //iteration implementation   
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int i = 0, j = i; i < s.length(); i++, j = i) {
            char ch = s.charAt(i);
            if (ch == ')') {
                stack.pollLast();
            } else if (ch >= '0' && ch <= '9' || ch == '-') {
                while (i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') {
                    i++;
                }
                TreeNode current = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peekLast();
                    //always start to construct the left child node of the parent first if it exists
                    if (parent.left != null) {
                        parent.right = current;
                    } else {
                        parent.left = current;
                    }
                }
                stack.offerLast(current);
            } 
        }
        return stack.isEmpty() ? null : stack.peek();
    }
}
