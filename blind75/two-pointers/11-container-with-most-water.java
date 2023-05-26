class Solution {
    // O(N): two pointer
    // T: O(n); single pass; S: O(1);
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;

        while (left < right) {
            int width = right - left;
            max = Math.max(max, width * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
