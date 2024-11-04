
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int[][] intervals1 = {{0,30},{5,10},{15,20}};
        System.out.println("Test case 1: " + solution.canAttendMeetings(intervals1));
        // Expected output: false

        // Test case 2
        int[][] intervals2 = {{7,10},{2,4}};
        System.out.println("Test case 2: " + solution.canAttendMeetings(intervals2));
        // Expected output: true

        // Test case 3
        int[][] intervals3 = {{1,2},{2,3},{3,4}};
        System.out.println("Test case 3: " + solution.canAttendMeetings(intervals3));
        // Expected output: true

        // Test case 4
        int[][] intervals4 = {{1,5},{5,8},{9,12}};
        System.out.println("Test case 4: " + solution.canAttendMeetings(intervals4));
        // Expected output: true

        // Test case 5
        int[][] intervals5 = {{4,6},{3,5},{2,4}};
        System.out.println("Test case 5: " + solution.canAttendMeetings(intervals5));
        // Expected output: false
    }
}

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        // Sort the intervals based on start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Check for any overlap
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }
        
        return true;
    }
}

