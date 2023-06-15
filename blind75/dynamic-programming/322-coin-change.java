class Solution {
    // DP: 1D 完全背包
    // f[i] : is the fewest number of coins for amount i;
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        if (n == 0) {
            return 0;
        }
        int[] f = new int[amount + 1];
        f[0] = 0;

        for (int i = 1; i <= amount; i++) {
            // initial
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (i >= coins[j] && f[i - coins[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i - coins[j]] + 1); // notice: add coins
                }
            }
        }
        return f[amount] != Integer.MAX_VALUE ? f[amount] : -1;
    }
}
