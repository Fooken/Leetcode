class Solution {
    // bottom up 
    //  设f[i][j]为A前i个字符A[0..i-1]和B前j个字符[0..j-1]的最长公共子串 的长度;
    // f[i][j] represents the length of the longest common subsequence between 
    // the substrings text1[0...i-1] and text2[0...j-1].
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] f = new int[m + 1][n + 1];
        int i, j;
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[m][n];
    }
    
    
    // Top down with memo;
    // state: dp(i, j) represents the longest common subsequence of text1(0...i) & text2(0...j)
    private Integer[][] memo;
    private String t1;
    private String t2;
    public int longestCommonSubsequence1(String text1, String text2) {
        this.t1 = text1;
        this.t2 = text2;

        int m = t1.length();
        int n = t2.length();
        memo = new Integer[m][n];
        return dp(m - 1, n - 1);
    }

    private int dp(int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        }

        if (memo[m][n] != null) {
            return memo[m][n];
        }

        int ans = 0;
        if (t1.charAt(m) == t2.charAt(n)) {
            ans = dp(m - 1, n - 1) + 1;
        } else {
            ans = Math.max(dp(m - 1, n), dp(m, n - 1));
        }

        memo[m][n] = ans;

        return memo[m][n];
    }
}
