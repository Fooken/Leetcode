class Solution {
    // T: O(n^2); S: O(1);
    private int ans = 0;
    public int countSubstrings1(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            isPalindromic(s, i, i); // single core
            isPalindromic(s, i, i + 1); // double core
        }
        return ans;
    }

    private void isPalindromic(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            ans++;
        }
    }
    // dp[i][j]: whether the substring composed of the i^th to the j^th characters of the input string, is a palindrome or not
    public int countSubstrings(String s) {
        int n = s.length();
        if (n <= 0) {
            return 0;
        }

        boolean[][] f = new boolean[n][n];

        int ans = 0;

        // Base case: single letter substrings
        for (int i = 0; i < n; i++) {
            f[i][i] = true;
            ans++;
        }

        // Base case: double letter substrings
        for (int i = 0; i < n - 1; i++) {
            f[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            ans += f[i][i + 1] ? 1 : 0;
        }

        // All other cases: substrings of length 3 to n
        int i, j, len;
        for (len = 3; len <= n; len++) {
            for (i = 0; i <= n - len; i++) {
                j = i + len - 1;
                f[i][j] = f[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                ans += f[i][j] ? 1 : 0;
            }
        }
        return ans;
    }
}
