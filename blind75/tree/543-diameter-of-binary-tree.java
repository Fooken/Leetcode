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
    /**
    Let N be the number of nodes in the tree.
    Time complexity: O(N). This is because in our recursion function longestPath, 
    we only enter and exit from each node once. We know this because each node is 
    entered from its parent, and in a tree, nodes only have one parent.

    Space complexity: O(N). The space complexity depends on the size of our implicit
    call stack during our DFS, which relates to the height of the tree. In the worst 
    case, the tree is skewed so the height of the tree is O(N). If the tree is balanced, 
    it'd be O(logN).
     */
    // Recursion
    private int res;
    public int diameterOfBinaryTree(TreeNode root) {
        res = 0;
        depthTree(root);
        return res;
    }

    private int depthTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = depthTree(root.left);
        int right = depthTree(root.right);

        res = Math.max(left + right, res);

        return Math.max(left, right) + 1;
    }
}
