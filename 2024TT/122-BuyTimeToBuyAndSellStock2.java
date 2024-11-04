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
}