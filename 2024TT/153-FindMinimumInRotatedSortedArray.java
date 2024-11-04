class Solution {
    // 20241026 - BinarySearch
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
        } else {
            return nums[right];
        }
    }
}

