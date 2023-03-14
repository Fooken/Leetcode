class Solution {
    // sliding window
    // 
    /**
    Sliding window:
    left & right
    用Map<Character, Integer> 来统计left的位置；
    遍历整个字符串，恰好遍历到Map存在的字母，此时就要重新选定left的位置；
    然后，就要统计此时最长的subString;
    遍历结束， return longest substring
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int left = 0;
        int maxLen = 0;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }

            maxLen = Math.max(maxLen, i - left + 1);
            map.put(c, i);
        }

        return maxLen;
    }
}
