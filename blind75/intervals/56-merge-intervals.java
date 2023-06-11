class Solution {
    // Ideas: Sorted and iterate: 
    // O(NlogN)
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, (o, p) -> Integer.compare(o[0], p[0]));

        int[] cur = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (cur[1] >= intervals[i][0]) {
                cur[1] = Math.max(intervals[i][1], cur[1]);
            } else {
                res.add(cur);
                cur = intervals[i];
            }
        }
        res.add(cur);

        return res.toArray(new int[res.size()][]);
    }
}
