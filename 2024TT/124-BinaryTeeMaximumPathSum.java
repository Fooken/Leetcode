
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: [1,2,3]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("Test case 1: " + solution.maxPathSum(root1));
        // Expected output: 6

        Solution solution1 = new Solution();
        // Test case 2: [-10,9,20,null,null,15,7]
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println("Test case 2: " + solution1.maxPathSum(root2));
        // Expected output: 42

        
        Solution solution2 = new Solution();
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test case 3: " + solution2.maxPathSum(root3));
    }
}

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

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { 
        this.val = val; 
    }
}

class Solution {
    // 20241103 Recursive Approach 
    // Recursion T:O(N)/ S:O(H) where H is a tree height
    private int maxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxPathSum;
    }
    
    private int maxPathSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftSum = Math.max(0, maxPathSumHelper(node.left));
        int rightSum = Math.max(0,maxPathSumHelper(node.right));
        
        // Compute the maximum path sum through the current node
        int pathSum = leftSum + rightSum + node.val;
        
        // Update the global maximum sum if necessary
        maxPathSum = Math.max(maxPathSum, pathSum);
        
        return Math.max(leftSum, rightSum) + node.val;
    }
}

