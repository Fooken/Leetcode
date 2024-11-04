import java.util.*;

/**
 * 
 * Find the maximum profit you can achieve. You may complete as many transactions as you like 
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 */

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test 1:
        int[] prices = {1,2,3,0,2};
        System.err.println("Test 1: " + sl.maxProfit(prices));
        // Output: 3

        // Test 2:
        int[] prices1 = {1};
        System.err.println("Test 1: " + sl.maxProfit(prices1));
        // Output: 0

    }
}

class Solution {
    /**
     * 3 states: withShare, cooldown, noShare
     *  withShare: withShare || cooldown - prices[i]
     *  cooldown: cooldown || noShare
     *  noShare: noShare || withShare + prices[i];
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;

        if (n == 0) {
            return 0;
        }

        int noShare = 0;
        int cooldown = 0;
        int withShare = -prices[0];

        for (int i = 1; i < n; i++) {
            withShare = Math.max(withShare, cooldown - prices[i]);
            cooldown = Math.max(cooldown, noShare);
            noShare = Math.max(noShare, withShare + prices[i]);
        }

        return noShare;
    }
    /**
     * Explanation:
     *   We use three arrays to represent different states:
            noShare[i]: Maximum profit on day i when not holding a share and not in cooldown
            withShare[i]: Maximum profit on day i when holding a share
            cooldown[i]: Maximum profit on day i when in cooldown (just sold a share)
     *    We initialize withShare[0] to -prices[0], as buying on the first day is the only possible action if we must hold a share.
     *    For each day:
            noShare[i]: We can either keep the previous noShare state or come from cooldown
            withShare[i]: We can either keep the previous withShare state or buy a new share (only possible from noShare state)
            cooldown[i]: We must have sold a share, so it comes from the previous withShare state plus the current price
     *   The final answer is the maximum of the last day's noShare and cooldown states.
     * 
     */
    // Time Complexity: O(n), where n is the number of days (length of the prices array). 
    // Space Complexity: O(n) for the three arrays. This can be optimized to O(1) by using just six variables instead of arrays.
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int n = prices.length;
        int[] noShare = new int[n];
        int[] withShare = new int[n];
        int[] cooldown = new int[n];
        
        // Initialize
        withShare[0] = -prices[0];
        
        // Dynamic Programming
        for (int i = 1; i < n; i++) {
            // Not holding a share: either keep previous state or come from cooldown
            noShare[i] = Math.max(noShare[i-1], cooldown[i-1]);
            
            // Holding a share: either keep previous state or buy a new share
            withShare[i] = Math.max(withShare[i-1], noShare[i-1] - prices[i]);
            
            // Cooldown: must come from selling a share
            cooldown[i] = withShare[i-1] + prices[i];
        }
        
        // The maximum profit is the max of last day's noShare and cooldown states
        return Math.max(noShare[n-1], cooldown[n-1]);
    }
}