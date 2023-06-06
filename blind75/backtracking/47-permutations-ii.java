class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, new HashSet<>());
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, Set<Integer> visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // if current element is already present in the temp, skip the element
            if (visited.contains(i)) continue; 
            // if prev element and current element is equal, skip the element
            if (i > 0 && nums[i] == nums[i - 1] && visited.contains(i - 1)) continue; // skip duplicated num
            list.add(nums[i]);
            visited.add(i);
            backtrack(res, list, nums, visited);

            //backtrack
            list.remove(list.size() - 1);
            visited.remove(i);
        }
    }
}
