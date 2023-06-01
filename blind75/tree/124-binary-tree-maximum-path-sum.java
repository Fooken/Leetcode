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
    // Recursion T:O(N)/ S:O(H) where H is a tree height
    private int maxGain = Integer.MIN_VALUE; // 可能有负值
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxGain;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, maxGain(node.left));
        int right = Math.max(0, maxGain(node.right));

        maxGain = Math.max(maxGain, left + right + node.val);

        return Math.max(left, right) + node.val;
    }
}
