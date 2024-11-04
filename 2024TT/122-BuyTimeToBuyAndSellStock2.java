import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test case 1:
        int[] prices1 = {7,1,5,3,6,4};
        System.err.println("Test case 1: " + sl.maxProfit(prices1));

        // Expected result: 7
    }
}
    // Hold at most one share of the stock at any time for each day
    // Status: 0: no share; 1: with share // dp[n][2]: dp[i][0] no share; dp[i][1]: withShare
    // state function: 
    //         dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); sell on day i
    //         dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] - prices[i]); just buy on day i
    // Notice: This approach is more versatile and can be extended to more complex stock trading problems.

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[n][2];

        // Initialize
        dp[0][0] = 0; // Maximum profit when we don't have a share
        dp[0][1] = -prices[0]; // Maximum profit when we have a share

        for (int i = 1; i < n; i++) {
            // Update noShare: either keep as is, or sell the share we bought previously
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // Update withShare: either keep as is, or buy a new share
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }
    
    // Dynamic Programming Approach
    /**
     * We use two arrays: noShare and withShare to keep track of the maximum profit in two states.
     * For each day, we updates both states based on the previous day's states
     * The final answer is in the last cell of noShare
     * 
     * Time Complexity: O(n), where n is the number of days. 
     * Space Complexity: O(n) for the two arrays. This can be optimized to O(1) by using just
     */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int n = prices.length;
        int[] noShare = new int[n];  // Maximum profit when not holding a share
        int[] withShare = new int[n];  // Maximum profit when holding a share
        
        // Initialize
        withShare[0] = -prices[0];
        
        // Dynamic Programming
        for (int i = 1; i < n; i++) {
            // Not holding a share: either keep previous state or sell the share
            noShare[i] = Math.max(noShare[i-1], withShare[i-1] + prices[i]);
            
            // Holding a share: either keep previous state or buy a new share
            withShare[i] = Math.max(withShare[i-1], noShare[i-1] - prices[i]);
        }
        
        // The maximum profit is in the last cell of noShare
        return noShare[n-1];
    }

    /**
     * 
     * Explanation:
        1. We iterate through the array once.
        2. Whenever the price on the current day is higher than the previous day, we add the difference to our profit.
        3. This approach essentially captures all upward price movements.
     */

    // Time Complexity: O(n), where n is the number of days (length of the prices array). 
    // Space Complexity: O(1), as we only use a constant amount of extra space.
    public int maxProfit3(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}