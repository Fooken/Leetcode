class Solution {
    // bit manipulation
    // 用异或来找同； 相同的两个数异或^就是零； 
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            // Notice: `res` also need to XOR with element 
            res ^= nums[i] ^ (i + 1);
        }
        return res;
    }
}
