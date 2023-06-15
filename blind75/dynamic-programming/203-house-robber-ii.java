class Solution {
    // like robber house 1:
    // 破开环： 假如有 0...n 个房子； 取 Math.min(0...n-1, 1...n);
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return nums[0];
        }

        int[] A = new int[n - 1];

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            A[i] = nums[i];
        }
        res = Math.max(res, robber(A));

        for (int i = 0; i < n - 1; i++) {
            A[i] = nums[i + 1];
        }

        res = Math.max(res, robber(A));

        return res;

    }
    
    private int robber(int[] nums) {
        int old = 0;
        int now = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = Math.max(now, old + nums[i]);
            old = now;
            now = temp;
        }
        return now;
    }
}
