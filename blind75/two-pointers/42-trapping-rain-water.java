class Solution {
    // Version 0: two pointers
    /*
    Time complexity: O(n). Single iteration of O(n)
    Space complexity: O(1) extra space. Only constant space required for left, right, left_max and right_max.
    */
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int left_max = 0, right_max = 0;
        int res = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                left_max = Math.max(height[left], left_max);
                res += (left_max - height[left]);
                ++left;
            } else {
                right_max = Math.max(height[right], right_max);
                res += (right_max - height[right]);
                --right;
            }
        }
        return res;
    }
}
