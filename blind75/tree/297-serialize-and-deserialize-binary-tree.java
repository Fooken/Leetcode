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

    // Encodes a tree to a single string.
    // V1: Recursion
    private static final String NA = "#";
    private static final String SPLITER = ",";

    public String serialize1(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return sb.append(NA).toString();
        }
        preorder(sb, root);
        return sb.toString();         
    }

    private void preorder(StringBuilder sb, TreeNode root) {
        if (root == null) {
            sb.append(NA).append(SPLITER);
        } else {
            sb.append(root.val).append(SPLITER);
            preorder(sb, root.left);
            preorder(sb, root.right);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        String[] str = data.split(SPLITER);
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(str));

        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals(NA)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.left = buildTree(queue);
        root.right = buildTree(queue);

        return root;
    }

    // V2: Iterative
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return sb.append(NA).toString();
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            
            if (cur == null) {
                sb.append(NA).append(SPLITER);
            } else {
                sb.append(cur.val).append(SPLITER);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] str = data.split(SPLITER);
        int idx = 0;
        
        if (str[idx].equals(NA)) {
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.valueOf(str[idx++]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (!str[idx].equals(NA)) {
                TreeNode left = new TreeNode(Integer.valueOf(str[idx++]));
                cur.left = left;
                queue.offer(cur.left);
            } else {
                cur.left = null;
                idx++;
            }
            
            if (!str[idx].equals(NA)) {
                TreeNode right = new TreeNode(Integer.valueOf(str[idx++]));
                cur.right = right;
                queue.offer(cur.right);
            } else {
                cur.right = null;
                idx++;
            }
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
