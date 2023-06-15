class Solution {
    // DP
    // f[i] = takes # of steps reach the i;
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] f = new int[n + 1];
        f[1] = 1;
        f[2] = 2;

        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f[n];
    }
    // DP, top down with memo
    private HashMap<Integer, Integer> memo = new HashMap<>();
    public int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        memo.put(n, climbStairs(n - 1) + climbStairs(n - 2));
        return memo.get(n);
    }
}
