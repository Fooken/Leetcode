class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //  time complexity is O(m*n) or O( sum of all chars in strs).
        if (strs.length == 0 || strs == null) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] freq = new char[26];
            for (char c: str.toCharArray()) {
                freq[c - 'a']++;
            }

            String strKey = String.valueOf(freq); // E.g. eat
            if (!map.containsKey(strKey)) {
                map.put(strKey, new ArrayList<>());
            }
            map.get(strKey).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
