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
    // Recursion T: O(N) S: O(N)
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }

        return p.val == q.val 
        && isSameTree(p.left, q.left) 
        && isSameTree(p.right, q.right);
    }
    
    // Iteration T: O(N)     S: O(N)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }        
        if (p == null || q == null) {
            return false;
        }
        
        Queue<TreeNode> pQ = new LinkedList<>();
        Queue<TreeNode> qQ = new LinkedList<>();
        pQ.offer(p);
        qQ.offer(q);
        
        
        while (!pQ.isEmpty() && !qQ.isEmpty()) {
            TreeNode pN = pQ.poll();
            TreeNode qN = qQ.poll();
            
            if (pN.val != qN.val) {
                return false;
            }
            
            if (pN.left != null && qN.left != null) {
                pQ.offer(pN.left);
                qQ.offer(qN.left);
            } else if (pN.left != null || qN.left != null) {
                return false;
            }
            
            if (pN.right != null && qN.right != null) {
                pQ.offer(pN.right);
                qQ.offer(qN.right);
            } else if (pN.right != null || qN.right != null) {
                return false;
            }
        }
        
        return true;
    }
}
