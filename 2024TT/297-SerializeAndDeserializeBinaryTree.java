import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();
        // Test case 1: [1,2,3,null,null,4,5]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        String serialized1 = sl.serialize(root1);
        System.out.println("Serialized 1: " + serialized1);
        TreeNode deserialized1 = sl.deserialize(serialized1);
        System.out.println("Reserialized 1: " + sl.serialize(deserialized1));

        // Test case 2: []
        TreeNode root2 = null;
        String serialized2 = sl.serialize(root2);
        System.out.println("Serialized 2: " + serialized2);
        TreeNode deserialized2 = sl.deserialize(serialized2);
        System.out.println("Reserialized 2: " + sl.serialize(deserialized2));

        // Test case 3: [1]
        TreeNode root3 = new TreeNode(1);
        String serialized3 = sl.serialize(root3);
        System.out.println("Serialized 3: " + serialized3);
        TreeNode deserialized3 = sl.deserialize(serialized3);
        System.out.println("Reserialized 3: " + sl.serialize(deserialized3));

        // Test case 4: [1,2,3,null,null,4,5,6,7]
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.right.left = new TreeNode(4);
        root4.right.right = new TreeNode(5);
        root4.right.left.left = new TreeNode(6);
        root4.right.left.right = new TreeNode(7);

        String serialized4 = sl.serialize(root4);
        System.out.println("Serialized 4: " + serialized4);
        TreeNode deserialized4 = sl.deserialize(serialized4);
        System.out.println("Reserialized 4: " + sl.serialize(deserialized4));
        
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // Encodes a tree to a single string.
    // V1: Recursion
    /**
     * 
     * Time Complexity:
     * Serialization: O(n), where n is the number of nodes in the tree.
     * Deserialization: O(n), as we process each node once.
     * Space Complexity:
     * Serialization: O(n) for the StringBuilder.
     * Deserialization: O(n) for the queue and the recursion stack.
     */

    private final String NULL_MARKER = "#";
    private final String DELIMITER = ",";

    // [1,2,3,null,null,4,5]
    // "1,2,#,#,3,4,#,#,5,#,#,"

    public String serialize(TreeNode root) {
        if (root == null) {
            return NULL_MARKER;
        }

        StringBuilder sb = new StringBuilder();
        buildString(sb, root);

        return sb.toString();
    }

    private void buildString(StringBuilder sb, TreeNode root) {
        if (root == null) {
            sb.append(NULL_MARKER).append(DELIMITER);
            return;
        }

        sb.append(root.val).append(DELIMITER);
        buildString(sb, root.left);
        buildString(sb, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }

        String[] str = data.split(DELIMITER);
        Queue<String> queue = new LinkedList<>(Arrays.asList(str));

        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String curr = queue.poll();

        if (curr.equals(NULL_MARKER)) {
            return null;
        } 
        TreeNode root = new TreeNode(Integer.parseInt(curr));
        root.left = buildTree(queue);
        root.right = buildTree(queue);
        
        return root;
    }
}