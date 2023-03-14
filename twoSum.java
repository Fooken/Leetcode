class Solution {
    // DS: HashMap/Array T: O(n)
    // Ideas: <element, idx>;  iterate elements;
    // if (map.containsKey(target - nums[i])): return solution;
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (map.containsKey(diff)) {
                res[1] = i;
                res[0] = map.get(diff);
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
