class Solution {
    // S: O(1)
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return nums[0];
        }

        int old = 0, now = nums[0];
        for (int i = 1; i < n; i++) {
            int temp = Math.max(now, old + nums[i]);
            old = now;
            now = temp;
        }
        return now;
    }
    // DP state: 其中 dp[i] to store the maximum amount that can be robbed up to each house;
    // state function: dp[i] = max(num[i] + dp[i - 2], dp[i - 1])
    // dp[i - 2] + nums[i]: rob this house
    // dp[i - 1] Non-rob
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return nums[0];
        }

        int[] f = new int[n];
        f[0] = nums[0];
        f[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            f[i] = Math.max(f[i - 1], f[i - 2] + nums[i]);
        }

        return f[n - 1];
    }
    // Recursion with Memoization
    private HashMap<Integer, Integer> memo = new HashMap<>();
    private int[] nums;
    public int rob1(int[] nums) {
        this.nums = nums;
        return helper(nums.length - 1);
    }
    private int helper(int n) {
        // base case
        if (n == 0) return nums[0];
        if (n == 1) return Math.max(nums[0], nums[1]);

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // save to memo, Recurrence relation
        memo.put(n, Math.max(helper(n - 1), helper(n - 2) + nums[n]));

        return memo.get(n);
    }
}
