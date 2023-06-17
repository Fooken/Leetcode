class Solution {
    // DP T:O(N^2)
    public int lengthOfLIS1(int[] nums) {
        // f[j]: 以nums[j]结尾的最长上升子序列的长度
        // f[i] = max(1, f[j] + 1);
        // max(a[i], 以nums[j]结尾的最长上升子序列的长度 + 1(a[i]))
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            f[i] = 1; // single num;

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            max = Math.max(max, f[i]);
        }
        return max;
    }
    
    // T: O[nlogN]
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] ends = new int[n];
        int index = 0;
        ends[index++] = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] > ends[index - 1]) {
                ends[index++] = nums[i];
            } else {
                // Notice: check new array: ends
                int lowerPos = lowerBand(ends, 0, index - 1, nums[i]);
                ends[lowerPos] = nums[i];
            }
        }

        return index;

    }

    private int lowerBand(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (nums[left] >= target) {
            return left;
        }
        if (nums[right] >= target) {
            return right;
        } else {
            return right + 1;
        }
    }
}
