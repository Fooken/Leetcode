class Solution {
    /**
    Time complexity : O(n), in length nnn of the string. We traverse over each character at-most once, 
    until the two pointers meet in the middle, or when we break and return early.
    Space complexity : O(1). No extra space required, at all.
     */
    public boolean isPalindrome(String s) {
        if (s.length() == 0 || s == null) {
            return true;
        }
        s = s.toLowerCase();

        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
