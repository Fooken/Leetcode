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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;
        if (sameTree(root, subRoot)) return true;

        return isSubtree(root.left, subRoot) 
        || isSubtree(root.right, subRoot);
        
    }

    private boolean sameTree(TreeNode q, TreeNode p) {
        if (p == null || q == null) {
            return p == q;
        }
        return p.val == q.val 
        && sameTree(p.right, q.right) 
        && sameTree(p.left, q.left);
    }
}
