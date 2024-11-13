import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test case 1:
        int[] nums = {1, 2, 3, 7};

        int[] res = sl.productExceptSelf(nums);

        System.out.print("[");
        for (int i = 0; i < res.length - 1; i++) {
            System.out.print(res[i] + ", ");
        }
        System.out.print(res[res.length - 1] + "]");

    }
}

class Solution {
    // DS: Array T:O(n); S:O(n)
    // Ideas: two way: go(the left of element) & back(the right of element); L/R/ans Arrays; ans = L*R;
    public int[] productExceptSelf0(int[] nums) {
        int len = nums.length;
        int[] L = new int[len];
        int[] R = new int[len];
        int[] ans = new int[len];
        
        // Note: for the element at index '0', there are no elements to the left,
        // so L[0] would be 
        L[0] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        //
        R[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }
        
        for (int i = 0; i < len; i++) {
            ans[i] = R[i] * L[i];
        }
        
        return ans;
    }
    // follow up: S:O(1)
    // Using a value(temp) instead of L/R array
    //    [1,2,3,4] 
    // -->[1,1,2,6]
    // <--[24,12,8,6]
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];

        // L -> R -->[1,1,2,6]
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = nums[i - 1] * res[i - 1];
        }

        // L <- R :[24,12,8,6]
        int R = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] = res[i] * R;
            R *= nums[i];
        }
        return res;
    }
}
