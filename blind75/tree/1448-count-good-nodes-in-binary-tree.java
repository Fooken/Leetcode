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
    // Depth First Search, Recursion
    // T: O(n) S: O(n)
    private int count;
    public int goodNodes1(TreeNode root) {
        count = 0;
        helper(root, Integer.MIN_VALUE);
        return count;
    }

    private void helper(TreeNode node, int max) {
        if (node == null) {
            return;
        }
        if (node.val >= max) {
            count++;
        }
        max = Math.max(node.val, max);
        helper(node.left, max);
        helper(node.right, max);
    }

    // Breadth First Search; 
    public int goodNodes(TreeNode root) {
        int count = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, Integer.MIN_VALUE));

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            if (cur.node.val >= cur.maxSoFar) {
                count++;
            }

            if (cur.node.left != null) {
                queue.offer(new Pair(cur.node.left, 
                Math.max(cur.node.val, cur.maxSoFar)));
            }
            if (cur.node.right != null) {
                queue.offer(new Pair(cur.node.right, 
                Math.max(cur.node.val, cur.maxSoFar)));
            }
        }
        return count;
    }
}
class Pair {
    public TreeNode node;
    public int maxSoFar;
    public Pair(TreeNode node, int maxSoFar) {
        this.node = node;
        this.maxSoFar = maxSoFar;
    }
}
