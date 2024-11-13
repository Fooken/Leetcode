import java.util.*;

public class Main {
    
        public static void main(String[] args) {
        Solution sl = new Solution();

        // Test case 1:
        String[] strs = {"eat", "ate", "ant", "at", "nat"};

        List<List<String>> res = sl.groupAnagrams(strs);

        System.out.println("Test starting....");
        
        if (res.isEmpty()) {
            System.out.println("Null, Empty");;
        }


        for (List<String> str: res) {
            System.out.print("[");
            for (int i = 0; i < str.size() - 1; i++) {
                System.out.print(str.get(i) + ", ");
            }
            System.out.print(str.get(str.size() - 1) + "]");
        }

    }
    
}

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //  time complexity is O(m*n) or O( sum of all chars in strs).
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] freq = new char[26];
            for (char c: str.toCharArray()) {
                freq[c - 'a']++;
            }

            String strKey = String.valueOf(freq); // E.g. eat
            map.computeIfAbsent(strKey, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
