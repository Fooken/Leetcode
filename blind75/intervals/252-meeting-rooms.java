class Solution {
    // Ideas: 存在overlap return false
    // T: O(NlogN) sort;
    public boolean canAttendMeetings(int[][] intervals) {
        // Sort the intervals based on the start time in ascending order
        // Arrays.sort(intervals, (o, p) -> Integer.compare(o[0], p[0]));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // Check for any overlapping meetings
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        // No overlapping meetings found
        return true;
    }
}
