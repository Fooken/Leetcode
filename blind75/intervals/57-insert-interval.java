class Solution {
    // Notice: intervals is sorted in ascending order by start.

    // [a, b]   ..... [d, e]
    // 
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> mergedIntervals = new ArrayList<>();

        int index = 0;

        // Add intervals before the new interval that do not overlap
        // [a, b] [new]
        while (index < intervals.length && intervals[index][1] < newInterval[0]) {
            mergedIntervals.add(intervals[index]);
            index++;
        }

        // Merge intervals that overlap with the new interval
         // [a, b] 
         //     [new]
        while (index < intervals.length && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
            index++;

        }
        mergedIntervals.add(newInterval);

        // Add remaining intervals after the new interval that do not overlap
        // [new] [a, b]
        while (index < intervals.length) {
            mergedIntervals.add(intervals[index]);
            index++;
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
