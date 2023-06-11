class Solution {
    // Ideas1: Sorted; PriorityQueue(sort by endtime of meeting)
    // 如果不满足 overlap; 就 poll from pq; else 加入pq; 最后判断pq size;
    
    public int minMeetingRooms1(int[][] intervals) {
        Arrays.sort(intervals, (o, p) -> Integer.compare(o[0], p[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval: intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) { // 完美衔接
                minHeap.poll();
            }
            minHeap.offer(interval[1]);
        }
        return minHeap.size(); 
    }
    
    // Ideas: Sweep line;
    // 为start和end加上权重；1/-1; 然后排序从小到大； 最后统计权重；
    public int minMeetingRooms(int[][] intervals) {
        List<int[]> meetings = new ArrayList<>();

        for (int[] interval: intervals) {
            meetings.add(new int[]{interval[0], 1});
            meetings.add(new int[]{interval[1], -1});
        }

        Collections.sort(meetings, (o, p) -> (o[0] == p[0] ? o[1] - p[1] : o[0] - p[0]));

        int minRooms = 0;
        int cnt = 0;
        for (int[] mt: meetings) {
            cnt += mt[1];
            minRooms = Math.max(minRooms, cnt);
        }

        return minRooms;
    }
}

