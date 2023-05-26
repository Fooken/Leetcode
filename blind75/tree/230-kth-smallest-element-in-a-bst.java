/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Iterative: T: O(h + k); s: O(n)
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            while (--k == 0) {
                return root.val;
            }

            root = root.right;
        }
        return -1;
    }

    // V2: Recursion:
    private int res;
    private int count;
    public int kthSmallest(TreeNode root, int k) {
        res = 0;
        count = 0;
        inorderTraversal(root, k);
        return res;
    }

    private void inorderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left, k);
        count++;
        if (count == k) {
            res = node.val;
            return;
        }

        inorderTraversal(node.right, k);
    }
}




public class Main {
    public static void main(String[] args) {
        // Create the BST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        // Find the 2nd smallest element
        int k = 2;
        Solution solution = new Solution();
        int result = solution.kthSmallest(root, k);
        System.out.println("The " + k + "th smallest element is: " + result);
    }
}
