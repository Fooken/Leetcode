import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test Case 1: 
        int[] prices1 = {7,1,5,3,6,4};
        System.err.println("Test case 1: " + sl.maxProfit(prices1));
    }
}

class Solution {
    // 20241031 
    // DS: Array; T:O(n)
    // Ideas: Greedy/One Pass/Sliding window 或者最大值 减去前面的最小值
    // we can maintain two variables: minPrice and maxProfit 
    // corresponding to the smallest valley and maximum;
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return maxProfit;
    }
}