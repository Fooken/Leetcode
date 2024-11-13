import java.util.*;

/**
 * Given two non-negative integers, num1 and num2 represented as string, 
 * return the sum of num1 and num2 as a string. 
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger). 
 * You must also not convert the inputs to integers directly.
 * 
 */
// Example 1:
// Input: num1 = "11", num2 = "123"
// Output: "134"

// Example 2:
// Input: num1 = "456", num2 = "77"
// Output: "533"

// Example 3:

// Input: num1 = "0", num2 = "0"
// Output: "0"


public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();
        // Test case 1:
        String num1 = "11", num2 = "123";
        System.out.println("Test case 1: " + sl.addStrings(num1, num2));

        // Test case 2:
        String num12 = "456", num22 = "77";
        System.out.println("Test case 1: " + sl.addStrings(num12, num22));

        // Test case 3: 
        String num13 = "0", nums23 = "0";
        System.out.println("Test case 1: " + sl.addStrings(num13, nums23));
    }
}

class Solution {
    public String addStrings(String num1, String num2) {
        int m = num1.length() - 1;
        int n = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (m >= 0 || n >= 0 || carry > 0) {
            int val1 = m >= 0 ? num1.charAt(m--) - '0' : 0;
            int val2 = n >= 0 ? num2.charAt(n--) - '0' : 0;

            int sum = val1 + val2 + carry;

            carry = sum / 10;
            sb.insert(0, sum % 10);
        }
        return sb.toString();
    }
}