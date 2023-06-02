class Solution {
    // DS: Array; T:O(n)
    // Ideas: Greedy/One Pass/Sliding window 或者最大值 减去前面的最小值
    // we can maintain two variables: minPrice and maxProfit 
    // corresponding to the smallest valley and maximum;
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int curPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - curPrice);
            curPrice = Math.min(curPrice, prices[i]);
        }
        return maxProfit;
    }
}
