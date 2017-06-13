/*
297. Serialize and Deserialize Binary Tree
Serialization is the process of converting a data structure or object into a 
sequence of bits so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed 
later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and 
this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. 
You do not necessarily need to follow this format, so please be creative and 
come up with different approaches yourself.
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
//level order implementation
public class Codec {
    private static final String spliter = ",";
    private static final String NN = "#";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return NN + spliter;
        } else {
            sb.append(root.val).append(spliter);
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.pollFirst();
            if (current.left != null) {
                sb.append(current.left.val).append(spliter);
                queue.offerLast(current.left);
            } else {
                sb.append(NN).append(spliter);
            }
            if (current.right != null) {
                sb.append(current.right.val).append(spliter);
                queue.offerLast(current.right);
            } else {
                sb.append(NN).append(spliter);
            }
        }
        return sb.toString();
    } 

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] values = data.split(spliter);
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
    
    private TreeNode generateNode(String s) {
        if (s.equals(NN)) {
            return null;
        } else {
            return new TreeNode(Integer.valueOf(s));
        }
    }
}

/************************************************************************************/
//preorder implementation
public class Codec {
    private static final String spliter = ",";
    private static final String NN = "#";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
            return;
        }
        sb.append(node.val).append(spliter);
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return deserializeHelper(nodes);
    }
    
    private TreeNode deserializeHelper(Deque<String> nodes) {
        String value = nodes.pollFirst();
        if (value.equals(NN)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = deserializeHelper(nodes);
        root.right = deserializeHelper(nodes);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
