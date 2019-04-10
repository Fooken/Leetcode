// Time O(n^2) ;
// Space O(n);
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1, sum = 0 - nums[i];
            while(left < right){
                if(nums[left] + nums[right] == sum){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    while(left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }else if(nums[left] + nums[right] < sum){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return res;
    }
}
    
/**
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        ArrayList<List<Integer>> res = new ArrayList<>();
        if( nums == null || nums.length <=2 ) return res;
        int i = 0;
        int n = nums.length;
        Arrays.sort(nums);
        
        while(i < n - 2){
            int base = nums[i];
            int left = i + 1;
            int right = n -1;
            
            while(left < right){
                int sum = base + nums[left] + nums[right];
                if(sum == 0){
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(base);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left = moveRight(nums, left + 1);
                    right = moveLeft(nums, right -1 );
                }else if(sum < 0){
                        left = moveRight(nums, left + 1);
                }else{
                        right = moveLeft(nums, right - 1);
                }
            }
            i = moveRight(nums, i+1);
        }
        return res;
    }
    
    public int moveLeft(int[] nums, int rightMinus){
        while(rightMinus == nums.length - 1 || (rightMinus >= 0 && nums[rightMinus] == nums[rightMinus + 1])){
            rightMinus--;
        }
        return rightMinus;
    }
    
    public int moveRight(int[] nums, int left){
        while(left == 0 || (left < nums.length && nums[left] == nums[left - 1])){
            left++;
        }
        return left;
    }
}
*/
