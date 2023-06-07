class Solution {
    /**
      N是candidates的数量, T是target, M是candidates中的最小值.
      时间复杂度 O(N^(T/M + 1))
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();

        backtracking(candidates, target, res, cur, 0);
        return res;
    }

    private void backtracking(int[] candidates, int target, List<List<Integer>> res, List<Integer> cur, int start) {
        // exceed the scope, stop exploration.
        if (target < 0) return;
        if (target == 0) {
            // make a deep copy of the current combination
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // add the number into the combination
            cur.add(candidates[i]);
            // The same number may be chosen from candidates an unlimited number of times
            backtracking(candidates, target - candidates[i], res, cur, i);
            // backtracking
            cur.remove(cur.size() - 1);
        }
    }
}
