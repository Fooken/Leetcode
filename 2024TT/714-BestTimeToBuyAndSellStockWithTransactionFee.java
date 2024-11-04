import java.net.Socket;
import java.util.*;


/**
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * Note:
 *  You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *  The transaction fee is only charged once for each stock purchase and sale.
 */

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test case 1:
        int[] prices = {1,3,2,8,4,9};
        int fee = 2;
        System.out.println("Test case 1: " + sl.maxProfit(prices, fee));
        // Output: 8
    }
}

class Solution {
    // 122 follow up with transaction fee
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int withShare = -prices[0];
        int noShare = 0;

        for (int i = 1; i < n; i++) {
            noShare = Math.max(noShare, withShare + prices[i] - fee);
            withShare = Math.max(withShare, noShare - prices[i]);
        }
        return noShare;
    }
}
