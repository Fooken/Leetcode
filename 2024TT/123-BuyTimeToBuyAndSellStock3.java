import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test case 1:
        int[] prices1 = {3,3,5,0,0,3,1,4};
        System.err.println("Test case 1: " + sl.maxProfit(prices1));

        // Expected result: 6


        // Test case 2:
        int[] prices2 = {1,2,3,4,5};
        System.err.println("Test case 2: " + sl.maxProfit1(prices2));

        // Expected result: 4
    }
}
/**
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * 4 states: buy1, sell1, buy2, sell2;
 * 
 * buy1:  has the first share   -> max(buy1, -prices[i])
 * sell1: sell the first share  -> max(sell1, buy1 + prices[i])
 * buy2:  has the second shares -> max(buy2, sell1 - prices[i]);
 * sell2: sell the second shares-> max(sell2, buy2 + prices[i]);
 * 
 * Time Complexity: O(n), where n is the number of days (length of the prices array). 
 * Space Complexity: O(n), as we use two 2D arrays of size 3 x n.
 * 
 */
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        int buy1 = -prices[0];
        int sell1 = 0;
        int buy2 = -prices[0];
        int sell2 = 0;

        for (int i = 1; i < n; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }

        return Math.max(sell1, sell2);

    }
    /**
     * 1. We use two 2D arrays: noShare and withShare.
     *  a. noShare[j][i] represents the maximum profit on day i after at most j transactions, not holding a share.
     *  b. withShare[j][i] represents the maximum profit on day i after at most j transactions, holding a share.
     * 2. We initialize withShare[i][0] to -prices[0] for both transactions, as buying on the first day is the only possible action if we must hold a share.
     * 3. For each day and each transaction:
     *  a. For noShare, we either keep the previous state or sell the share we're holding.
     *  b. For withShare, we either keep the previous state or buy a share using the profit from the previous transaction.
     * 4. The final answer is in noShare[2][n-1], which represents the maximum profit after at most two transactions, ending without holding a share.
     * 
     * Time Complexity: O(n), where n is the number of days (length of the prices array). 
     * Space Complexity: O(n), as we use two 2D arrays of size 3 x n.
     */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int n = prices.length;
        int[][] noShare = new int[3][n];
        int[][] withShare = new int[3][n];
        
        // Initialize
        for (int i = 1; i <= 2; i++) {
            withShare[i][0] = -prices[0];
        }
        
        // Dynamic Programming
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 2; j++) {
                // Not holding a share
                noShare[j][i] = Math.max(noShare[j][i-1], withShare[j][i-1] + prices[i]);
                // Holding a share
                withShare[j][i] = Math.max(withShare[j][i-1], noShare[j-1][i-1] - prices[i]);
            }
        }
        
        // The maximum profit is in the last cell of noShare for the second transaction
        return noShare[2][n-1];
    }
}