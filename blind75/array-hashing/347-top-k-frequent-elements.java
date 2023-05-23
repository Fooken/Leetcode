class Solution {
    // V1: PriorityQueue heap; T:O(NlogK); S: O(N + k)
    public int[] topKFrequent(int[] nums, int k) {
        
        if (nums.length <= k) {
            return nums;
        }
        

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o, p) -> (freq.get(o) - freq.get(p)));

        for (int num: freq.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] topK = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            topK[i] = minHeap.poll();
        }
        return topK;
    }
    // V2: Best Solution for quick select(O(n) vs quicksort O(nlogN)) 
    private Map<Integer, Integer> freq;
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length <= k) {
            return  nums;
        }
        freq = new HashMap<>();
        for (int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // unique array for key
        int[] unique = new int[freq.size()];
        int i = 0;
        for (int num: freq.keySet()) {
            unique[i++] = num;
        }

        quickSelect(unique, 0, i - 1, k);
        return Arrays.copyOfRange(unique, 0, k);
    }

    private void quickSelect(int[] nums, int start, int end, int kth) {
        if (start == end) {
            return;
        }
        int pos = partition(nums, start, end);

        if (pos == kth) {
            return;
        } else if (pos < kth) {
            quickSelect(nums, pos + 1, end, kth);
        } else {
            quickSelect(nums, start, pos - 1, kth );
        }
    }
    // 1, 2, 3
    // 3, 2, 1

    private int partition(int[] nums, int start, int end) {
        Random rand = new Random();
        int pivot = rand.nextInt(end - start) + start;
        int pivotFreq = freq.get(nums[pivot]);

        // Step 1: Move pivot at the end of the array using swap;
        swap(nums, pivot, end);

        // Step 2: Set the pointer at the beginning of the array store_index = left
        int store_index = start;
        for (int i = start; i < end; i++) {
            if (freq.get(nums[i]) > pivotFreq) {
                swap(nums, store_index, i);
                store_index++;
            }
        }

        // Step 3: swapping pivot to the final pivot location
        swap(nums, store_index, end);
        return store_index;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
