class Solution {
    // Two Pointer: sorted array; i: left: i + 1, right: nums.length -1;
    // Reminder: duplicated
    // T: O(n^2); 
    // S: from O(log⁡n) to O(n); depending on the implementation of the sorting algorithm
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // sort: nlogN
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            twoSum(nums, i, res);
        }

        return res;
    }

    private void twoSum(int[] nums, int i, List<List<Integer>> res) {
        int left = i + 1;
        int right = nums.length - 1;
        int sum = 0;
        while (left < right) {
            sum = nums[i] + nums[left] + nums[right];
            if (sum == 0) {
                res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                while (left + 1 < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                while (right - 1 > left && nums[right] == nums[right - 1]) {
                    right--;
                }
                left++;
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
    }
}
