import java.util.*;


// Given an integer array nums, 
// find three numbers whose product is maximum and return the maximum product.

// Example 1:

// Input: nums = [1,2,3]
// Output: 6

// Example 2:

// Input: nums = [1,2,3,4]
// Output: 24

// Example 3:

// Input: nums = [-1,-2,-3]
// Output: -6


// - - + 
// + + +

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test 1:
        int[] nums1 = {1,2,3};
        System.out.println("Output: " + sl.maximumProduct(nums1));

        // Test 2:
        int[] nums2 = {1,2,3,4};
        System.out.println("Output: " + sl.maximumProduct(nums2));

        // Test 3:
        int[] nums3 = {-1,-2,-3};
        System.out.println("Output: " + sl.maximumProduct(nums3));
        
        // Test 4:
        int[] nums4 = {-3,-2,-1, 0};
        System.out.println("Output: " + sl.maximumProduct(nums4));
        // Test 1:
        int[] nums5 = {-3,-2,-1, 0, 1, 2};
        System.out.println("Output: " + sl.maximumProduct(nums5));

    }
}
class Solution {
    public int maximumProduct(int[] nums) {
        // int len = nums.length;
        // if (len == 3) {
        //     return nums[0] * nums[1] * nums[2];
        // }


        // Arrays.sort(nums);
        // int max = Integer.MIN_VALUE;
        // // - - +
        // int m1 = nums[0] * nums[1] * nums[len - 1];
        // // + + +
        // int m2 = nums[len - 3] * nums[len - 2] * nums[len - 1];
        
        // return Math.max(max, Math.max(m1, m2));

        // Upgrade
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int n: nums){
            if(n > max1){
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if(n > max2){
                max3 = max2;
                max2 = n;
            } else if(n > max3){
                max3 = n;
            }

            if(n < min1){
                min2 = min1;
                min1 = n;
            } else if(n < min2){
                min2 = n;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}