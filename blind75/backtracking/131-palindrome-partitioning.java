class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), s, 0);
        return res;
    }

    private void backtrack(List<List<String>> res, List<String> list, String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(list));
        }

        for (int i = start; i < s.length(); i++) {
            if (isPalindorme(s, start, i)) {
                list.add(s.substring(start, i + 1));
                backtrack(res, list, s, i + 1);
                list.remove(list.size() - 1);

            }
        }
    }

    private boolean isPalindorme(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
