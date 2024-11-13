import java.util.*;
import javax.swing.plaf.SliderUI;




/**
 * Given a string s and an integer k, return the length of the longest substring of s 
 * such that the frequency of each character in this substring is greater than or equal to k.
 * 
 * if no such substring exists, return 0.
 * 
 *  Example 1: 
 *    Input: s = "aaabb", k = 3 
 *    Output: 3
 *    Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.   
 */

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test case 1:
        String s1 = "aaabb";
        int k1 = 3;

        System.out.println("Test case 1: " + sl.longestSubstring(s1, k1));

        // Test case 2:
        // Input: s = "ababbc", k = 2
        // Output: 5
        String s2 = "ababbc";
        int k2 = 2;

        System.out.println("Test case 2: " + sl.longestSubstring(s2, k2));

    }
}
// Divide & Conquer
class Solution {
    public int longestSubstring(String s, int k) {
        return helper(s.toCharArray(), 0, s.length(), k);
    }

    private int helper(char[] arr, int left, int right, int k) {
        if (right - left < k) {
            return 0;
        }
        int[] freq = new int[26];
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i] - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] < k && freq[i] > 0) {
                // find split point
                for (int j = left; j < right; j++) {
                    if (arr[j] == i + 'a') {
                        int leftLength = helper(arr, left, j, k);
                        int rightLength = helper(arr, j + 1, right, k);

                        return Math.max(leftLength, rightLength);
                    }
                }
            }
        }
        return right - left;
    } 
}