class Solution {
    // Time complexity: O(n). 
    // Space complexity: O(1).
    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length == 0 || numbers == null) {
            return new int[2];
        }
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[]{left + 1, right + 1};
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            } 
        }
        // In case there is no solution, return {-1, -1}.
        return new int[2];
    }
}
