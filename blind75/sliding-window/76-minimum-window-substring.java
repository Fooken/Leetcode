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

        int left = 0, right, count = 0, minLen = s.length() + 1;
        String res = "";

        for (right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) - 1);
                if (freqMap.get(c) >= 0) {
                    count++;
                }
            }

            while (count == t.length()) {
                if (minLen > right - left + 1) {
                    minLen = right - left + 1;
                    res = s.substring(left, right + 1);
                }

                // Shrink left pointer;
                char lc = s.charAt(left);
                if (freqMap.containsKey(lc)) {
                    freqMap.put(lc, freqMap.get(lc) + 1);
                    if (freqMap.get(lc) > 0) {
                        count--;
                    }
                }
                left++;
            }
        }
        return res;
    }
}
