
import java.util.*;
/**
 * Explanation:
    1. We first check if the input array is null or empty. If so, we return an empty list.
    2. We create a HashMap where:

    2.1 The key is the sorted version of each string (which will be the same for all anagrams).
    2.2 The value is a list of strings that are anagrams of each other.
    
    3. We iterate through each string in the input array:
    3.1 Convert the string to a char array and sort it.
    3.2 Use this sorted string as a key in our map.
    3.3 If this key doesn't exist in the map, we create a new ArrayList for it.
    3.4 We add the original string to the list corresponding to this key.
    
    4. Finally, we return a new ArrayList containing all the values from our map, which are the grouped anagrams.

    Time Complexity: O(N * K * log K), where N is the number of strings, and K is the maximum length of a string in strs. This is because for each string, we perform a sorting operation.
    Space Complexity: O(N * K) to store the HashMap.
    Here's a main method to test the solution:
 */

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();
        // Test case 1
        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        System.out.println("Test case 1: " + sl.groupAnagrams(strs1));
        // Expected output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        
        // Test case 2
        String[] strs2 = {""};
        System.out.println("Test case 2: " + sl.groupAnagrams(strs2));
        // Expected output: [[""]]
        
        // Test case 3
        String[] strs3 = {"a"};
        System.out.println("Test case 3: " + sl.groupAnagrams(strs3));
        // Expected output: [["a"]]
    }
}

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String word: strs) {
            char[] strArr = new char[26]; // notice 
            for (char c: word.toCharArray()) {
                strArr[c - 'a']++;
            }
            String strKey = String.valueOf(strArr);
            map.computeIfAbsent(strKey, k -> new ArrayList<String>()).add(word); // k -> new ArrayList
        }
        
        return new ArrayList<>(map.values());
    }
}

