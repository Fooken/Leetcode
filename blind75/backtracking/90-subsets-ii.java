class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void backtracking(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
        res.add(new ArrayList<>(list));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicated
            list.add(nums[i]);
            backtracking(res, list, nums, i + 1);
            // backtracking
            list.remove(list.size() - 1);
        }
    }
}
