class Solution {
    // V1: T: O(n) S: O(n)
    
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num: nums) {
            set.add(num);
        }
        int res = 0;

        for (int num: nums) {
            int pre = num - 1, next = num + 1;

            if (set.remove(num)) {
                while (set.remove(pre)) pre--;
                while (set.remove(next)) next++;
            }

            res = Math.max(res, next - pre - 1);
        }
        return res;
    }
    
    // V2 Union-Find Solution
    public int longestConsecutive(int[] nums) {
        DSU dsu = new DSU(nums.length);
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            // corner case, if we have duplicated elements;
            if (map.containsKey(nums[i])) continue;
            map.put(nums[i], i);
            
            if (map.containsKey(nums[i] + 1)) {
                dsu.union(i, map.get(nums[i] + 1));
            }
            if (map.containsKey(nums[i] - 1)) {
                dsu.union(i, map.get(nums[i] - 1));
            }
        }
        return dsu.findMax();
    }
}

// implement with size
class DSU {
    int[] parent;
    int[] size;
    public DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        Arrays.fill(size, 1);
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) return;
        if (size[rootX] <= size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else if (size[rootX] > size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }
    
    public int findMax() {
        int max = 0;
        for (int s: size) {
            max = Math.max(s, max);
        }
        return max;
    }
}


