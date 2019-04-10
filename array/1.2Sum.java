class Solution{
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] res = new int[2];
        
        for(int i = 0; i < nums.length; i++){
            
            if(map.containsKey(target - nums[i])){
                
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                break;
                
            }
            map.put(nums[i],i);
            
        }
        return res;
    }
}




/**
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int[] res = new int[2];
        
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i],i);
        }
        for(int i = 0; i < nums.length; i++){
            int t = target - nums[i];
            if(map.containsKey(t) && map.get(t) != i){
                res[0] = i;
                res[1] = map.get(t);
                break;
            }
        }
        return res;
    }
}
*/