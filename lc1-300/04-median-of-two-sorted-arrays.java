// V0
// Divide && Conquer O(log(m + n));
    /**
     * 通过 divide conquer 线分解： length of both array: n -> 2
     * 开始时判断长度为even or odd;
     * 递归求解： findKthElement(A, indexA, B, indexB, k);
     *    跳出递归的条件是：1. indexA >= A.length 2: indexB >= B.length 3: k == 1
     *    halfKthOfA = startOfA + k / 2 - 1 < A.length ? A[startOfA + k / 2 - 1] : Integer.MAX_VALUE;
     *    halfKthOfB = startOfB + k / 2 - 1 < B.length ? B[startOfA + k / 2 - 1] : Integer.MAX_VALUE;
     *    判断 halfKthOfA / halfKthOfB 进一步递归:
          if (halfKthOfA < halfKthOfB) {
              return findKth(A, indexA + k / 2, B, indexB, k - k / 2);
          }
          return findKth(A, indexA, B, indexB + k / 2, k - k / 2);
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 0) {
            return (findKth(nums1, 0, nums2, 0, len / 2) + findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
        }
        return findKth(nums1, 0, nums2, 0, len / 2 + 1);
    }

    private double findKth(int[] A, int startOfA, int[] B, int startOfB, int k) {
        if (startOfA >= A.length) {
            return B[startOfB + k - 1];
        }
        if (startOfB >= B.length) {
            return A[startOfA + k - 1];
        }
        if (k == 1) {
            return Math.min(A[startOfA], B[startOfB]);
        }

        int halfKthOfA = startOfA + k / 2 - 1 < A.length ? A[startOfA + k / 2 - 1] : Integer.MAX_VALUE;
        int halfKthOfB = startOfB + k / 2 - 1 < B.length ? B[startOfB + k / 2 - 1] : Integer.MAX_VALUE;


        if (halfKthOfA < halfKthOfB) {
            return findKth(A, startOfA + k / 2, B, startOfB, k - k / 2);
        }
        return findKth(A, startOfA, B, startOfB + k / 2, k - k / 2);
    }



    // 二分答案的方法，时间复杂度  O(log(range) * (log(n) + log(m)))
    // 其中 range 为最小和最大的整数之间的范围。 可以拓展到 Median of K Sorted Arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        if (n % 2 == 0) {
            return (findKth(nums1, nums2, n / 2) + findKth(nums1, nums2, n / 2 + 1)) / 2.0;
        }
        // Notice
        return findKth(nums1, nums2, n / 2 + 1);
    }
    
    // find first x that >= k numbers is smaller or equal to x
    private int findKth(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        // Notice 
        if (n == 0) {
            return nums2[k - 1];
        }
        if (m == 0) {
            return nums1[k - 1];
        }
        
        int left = Math.min(nums1[0], nums2[0]);
        int right = Math.max(nums1[n - 1], nums2[m - 1]);
        // [3] [-2, -1]
        while (left + 1 < right) {
            int midValue = left + (right - left) / 2;
            if (countSmallerOrEqual(nums1, midValue) + countSmallerOrEqual(nums2, midValue) < k) {
                left = midValue;
            } else {
                right = midValue;
            }
        }
        
        if (countSmallerOrEqual(nums1, left) + countSmallerOrEqual(nums2, left) >= k) {
            return left;
        }
        return right;
    }
    
    private int countSmallerOrEqual(int[] nums, int val) {
        int low = 0, high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= val) {
                low = mid;
            } else {
                high = mid;
            }
        }
        
        if (nums[low] > val) {
            return low;
        }
        if (nums[high] > val) {
            return high;
        }
        return nums.length;
    }
