class Solution {
    
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }

        // Sort the intervals based on the end time in ascending order
        // Arrays.sort(intervals, (o, p) -> Integer.compare(o[1], p[1]));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));


        int count = 1; // Number of non-overlapping intervals
        int end = intervals[0][1];

        // Iterate through the intervals and check for overlaps
        for (int[] interval: intervals) {
            // non-overlapping
            if (end <= interval[0]) {
                // No overlap, update the end time and increment the count
                end = interval[1];
                count++;
            }
        }

        return intervals.length - count;
    }
}
