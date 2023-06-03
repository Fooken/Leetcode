class Solution {
    // Ideas: BinarySearch
    // 本质都是在寻找一个非单调递增区间，这个区间就包含了最小值。
    // if: nums[mid] > nums[right] : non-mono: mid~right; 
    // else left ~ mid;
    // T: O(logN) S: O(1);
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (nums[left] <= nums[right]) {
            return nums[left];
        }
        if (nums[right] < nums[left]) {
            return nums[right];
        }
        return -1;
    }
}
