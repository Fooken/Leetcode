import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test case 1:
        int k = 2;
        int[] prices1 = {2,4,1};
        System.err.println("Test case 1: " + sl.maxProfit(k, prices1));
        // Expected result: 2


        // Test case 2:
        int k2 = 2;
        int[] prices2 = {3,2,6,5,0,3};
        System.err.println("Test case 2: " + sl.maxProfit(k2, prices2));
        // Expected result: 7
    }
}

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 * 
 * Find the maximum profit you can achieve. 
 * You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
 */

class Solution {
    // 123 follow up: buy at most k times and sell at most k times.
    // buy[k + 1]; sell[k + 1];
    // state function:
    // 
    // buy[i] = max(buy[i], sell[i] - prices[i]) 
    // sell[i] = max(sell[i], buy[i - 1] + prices[i])
    public int maxProfit1(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[] buy = new int[k + 1]; // 1,2...k 对应index
        int[] sell = new int[k + 1];// 1,2...k 对应index
        Arrays.fill(buy, Integer.MIN_VALUE / 2); // buy + sell /2 防止overflow
        Arrays.fill(sell, Integer.MIN_VALUE / 2);
        buy[0] = -prices[0];
        sell[0] = 0;

        for (int i = 1; i < n; i++) {
            buy[0] = Math.max(buy[0], sell[0] - prices[i]); // 第一次买入
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j - 1] + prices[i]);
            }
        }


        int res = 0;
        for (int i = 1; i <= k; i++) {
            res = Math.max(sell[i], res);
        }
        return res;
    }
    // Using dynamic programming with two states: 
    // noShare (not holding a share) and withShare (holding a share).
    /**
     * 
     * Explanation: 
     * 1. We use two 2D arrays: noShare and withShare.
     *  a. noShare[j][i] represents the maximum profit on day i after at most j transactions, not holding a share.
     *  b. withShare[j][i] represents the maximum profit on day i after at most j transactions, holding a share.
     * 2. We initialize withShare[i][0] to -prices[0] for both transactions, as buying on the first day is the only possible action if we must hold a share.
     * 3. For each day and each transaction:
     *  a. For noShare, we either keep the previous state or sell the share we're holding.
     *  b. For withShare, we either keep the previous state or buy a share using the profit from the previous transaction.
     * 4. The final answer is in noShare[2][n-1], 
     * which represents the maximum profit after at most two transactions, ending without holding a share.
     */
    // Time Complexity: O(n * k), where n is the number of days and k is the maximum number of transactions allowed. 
    // Space Complexity: O(n * k) for the two 2D arrays.
     public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1 || k <= 0) {
            return 0;
        }
        
        int n = prices.length;
         
        // If k is large enough, we can make as many transactions as we want
        // same with 122. Best Time to Buy and Sell Stock II
        if (k >= n / 2) {
            return maxProfitUnlimited(prices);
        }
         
        int[][] noShare = new int[k + 1][n];
        int[][] withShare = new int[k + 1][n];
        
        // Initialize
        for (int i = 1; i <= k; i++) {
            withShare[i][0] = -prices[0];
        }
        
        // Dynamic Programming
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // Not holding a share
                noShare[j][i] = Math.max(noShare[j][i-1], withShare[j][i-1] + prices[i]);
                
                // Holding a share
                withShare[j][i] = Math.max(withShare[j][i-1], noShare[j-1][i-1] - prices[i]);
            }
        }
        
        // The maximum profit is in the last cell of noShare for the second transaction
        return noShare[k][n-1];
    }
    // Simple One-Pass Approach
    private int maxProfitUnlimited(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }
}