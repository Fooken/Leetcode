import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test case 1 :
        // inPut: [1,2,3,4,7] target:6
        int[] nums = {1,2,3,4,7};
        int target = 8;

        System.err.println("Test case 1: " + printArray(sl.twoSum(nums, target)));


        // Test case 2 :
        // inPut: [1,2,3,4,7] target:6
        int[] nums1 = {1,2,3,4,7};
        int target1 = 12;

        System.err.println("Test case 2: " + printArray(sl.twoSum(nums1, target1)));

    }

    private static String printArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        } else {
            return "[" + nums[0] + ". " + nums[1] +"]";
        }
    }
}

class Solution {
    // DS: HashMap/Array T: O(n)
    // Ideas: <element, idx>;  iterate elements;
    // if (map.containsKey(target - nums[i])): return solution;
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                res[1] = i;
                res[0] = map.get(diff);
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    // 20241105
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                res[1] = i;
                res[0] = map.get(diff);
                return res;
            }

            map.put(nums[i], i);
        }
        return null;
    }
}
