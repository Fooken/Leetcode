class Solution {
    // Deque
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        // The deque is kept in descending order, with the maximum element always at the peek()/peekFirst()
        Deque<Integer> deque = new ArrayDeque<>(); 
        int ri = 0;

        for (int i = 0; i < n; i++) {
            // Remove elements that are out of the current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove smaller elements from the deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add the current element to the deque
            deque.offerLast(i);

            // Process the maximum element in the current window
            if (i >= k - 1) {
                res[ri++] = nums[deque.peekFirst()];
            }
        }

        return res;
    }
    // Bruteforce: T: O(Nk), TLE
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        int maxS = Integer.MIN_VALUE;
        for (int num: nums) {
            maxS = Math.max(maxS, num);
        }
        if (k > n) {
            return new int[]{maxS};
        }

        int[] res = new int[n - k + 1];
        int left = 0;

        while (left < n - k + 1) {
            int right = left;
            int max = Integer.MIN_VALUE;
            int size = k;
            while (size-- > 0) {
                max = Math.max(nums[right++], max);
            }
            res[left] = max;
            left++;
        }
        return res;
    }
}
