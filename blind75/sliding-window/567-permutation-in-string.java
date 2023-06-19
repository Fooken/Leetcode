class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        HashMap<Character, Integer> s1Map = new HashMap<>();
        HashMap<Character, Integer> s2Map = new HashMap<>();

        // s1 to s1Map
        for (char c: s1.toCharArray()) {
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int matched = 0;

        while (right < s2.length()) {
            char rightChar = s2.charAt(right);
            // s2 to s2Map
            s2Map.put(rightChar, s2Map.getOrDefault(rightChar, 0) + 1);

            // If the current character in s2 is present in s1, increment the matched count
            if (s1Map.containsKey(rightChar) && s2Map.get(rightChar) <= s1Map.get(rightChar)) {
                matched++;
            }

            // If all characters in s1 are matched with the current window in s2, return true
            if (matched == s1.length()) {
                return true;
            }

            // Shrink the window if it exceeds the length of s1
            while (right - left + 1 >= s1.length()) {
                char leftChar = s2.charAt(left);

                if (s1Map.containsKey(leftChar) && s2Map.get(leftChar) <= s1Map.get(leftChar)) {
                    matched--;
                }

                s2Map.put(leftChar, s2Map.get(leftChar) - 1);

                if (s2Map.get(leftChar) == 0) {
                    s2Map.remove(leftChar);
                }
                left++;
            }
            right++;
        }
        return false;
    }
}
