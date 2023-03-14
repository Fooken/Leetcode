class Solution {
    // 基于中心向两边枚举               
    // T O(n^2) Space O(1);
    private String lps = "";
    public String longestPalindrome(String s) {
        int n = s.length();
        //corner case
        if (n == 0 || s == null) {
            return lps;
        }
        for (int i = 0; i < n; i++) {
            helper(s, i, i); // single core
            helper(s, i, i + 1); // double core
        }
        return lps;
    }

    private void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        String cur = s.substring(left + 1, right);
        if (lps.length() < cur.length()) {
            lps = cur;
        }
    }
}
