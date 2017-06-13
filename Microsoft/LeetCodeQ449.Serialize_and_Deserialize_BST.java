/*
449. Serialize and Deserialize BST
Serialization is the process of converting a data structure or object into a 
sequence of bits so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later 
in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary search tree can be serialized to a string and 
this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. 
Your serialize and deserialize algorithms should be stateless.
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
public class Codec {
    //如果input的数字里面是单个digital，就不需要分隔符，这个时候最compact的状态
    private static final String NN = "#";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return NN;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.pollFirst();
            if (current.left != null) {
                sb.append(current.left.val);
                queue.offerLast(current.left);
            } else {
                sb.append(NN);
            }
            if (current.right != null) {
                sb.append(current.right.val);
                queue.offerLast(current.right);
            } else {
                sb.append(NN);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        char[] values = data.toCharArray();
        int index = 0;
        TreeNode root = generateNode(values[index++]);
        Deque<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offerLast(root);
        }
        while (!queue.isEmpty()) {
            TreeNode current = queue.pollFirst();
            current.left = generateNode(values[index++]);
            current.right = generateNode(values[index++]);
            if (current.left != null) {
                queue.offerLast(current.left);
            }
            if (current.right != null) {
                queue.offerLast(current.right);
            }
        }
        return root;
    }
    
    private TreeNode generateNode(char ch) {
        if (ch == '#') {
            return null;
        }
        return new TreeNode(Character.getNumericValue(ch));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
