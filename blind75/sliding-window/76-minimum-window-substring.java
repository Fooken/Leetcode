class Solution {
    // Sliding Window
    /**
        #1 Using Freq Map recode String t;
        #2 traverse string s, if find same character with freq map: 
         - recode count number(same with t);
           - Once count == t.length();
           a. Save result substring(left, right + 1);
           b. Shrink left pointer;
     */
    public String minWindow(String s, String t) {
        // Freq Map for String t
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c: t.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, count = 0, minLen = s.length() + 1;
        String res = "";

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (freqMap.containsKey(rightChar)) {
                freqMap.put(rightChar, freqMap.get(rightChar) - 1);

                if (freqMap.get(rightChar) >= 0) {
                    count++;
                }
            }

            while (count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    res = s.substring(left, right + 1);
                }

                // Shrink left pointer;
                char leftChar = s.charAt(left);
                if (freqMap.containsKey(leftChar)) {
                    freqMap.put(leftChar, freqMap.get(leftChar) + 1);
                    if (freqMap.get(leftChar) > 0) {
                        count--;
                    }
                }
                left++;
            }
            right++;
        }
        return res;
    }
}
