class Solution {
    // Top down + DP Time complexity : O(2^n) Space complexity : O(2^n)
    private Boolean[] memo;
    public boolean wordBreak1(String s, List<String> wordDict) {
        int n = s.length();
        memo = new Boolean[n];

        return dfs(s, 0, new HashSet<>(wordDict));
    }

    private boolean dfs(String s, int start, HashSet<String> dict) {
        if (start >= s.length()) {
            return true;
        }

        if (memo[start] != null) {
            return memo[start];
        }

        for (int i = start + 1; i <= s.length(); i++) {
            if (dict.contains(s.substring(start, i)) && dfs(s, i, dict)) {
                return memo[start] = true;
            }
        }

        return memo[start] = false;
    }
    // Time complexity : O(n^2) There are two nested loops, 
    // and substring computation at each iteration
    // f[i] stands for whether subarray(0, i) can be segmented into words from the dictionary
    // Space complexity : O(n)
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[0] = true; // since the null string is always present in the dictionary

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && wordSet.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
