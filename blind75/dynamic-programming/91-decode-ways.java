class Solution {
    // DP: T:O(N)/S:O(N)
    // f[i]: 设字符串s前i个数字解码成字母有f[i]种方式
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int[] f = new int[s.length() + 1];
        f[0] = 1; // empty string 1 way;
        f[1] = 1; // '1' 

        // Process the string character by character; index from 1;
        for (int i = 2; i <= s.length(); i++) {
            // Check if the current character is a valid single-digit number
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            // Check if the current and previous characters form a valid two-digit number
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigits >= 10 && twoDigits <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[s.length()];
    }
}
