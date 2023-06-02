class Solution {
    // Sliding Window
    /**
        Notice: window size - maxFreq > k  --> left++
        max = right - left + 1;
        
        "Freq array to get maxFreq"
     */
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int maxFreq = 0, max = 0;
        int left = 0, right;

        for (right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'A']++;
            // maxFreq of str
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            // window size - maxFreq > k 
            // move to left
            while (right - left + 1 > maxFreq + k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
