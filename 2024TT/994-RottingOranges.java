import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int[][] grid1 = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println("Test case 1: " + solution.orangesRotting(grid1));
        // Expected output: 4

        // Test case 2
        int[][] grid2 = {{2,1,1},{0,1,1},{1,0,1}};
        System.out.println("Test case 2: " + solution.orangesRotting(grid2));
        // Expected output: -1

        // Test case 3
        int[][] grid3 = {{0,2}};
        System.out.println("Test case 3: " + solution.orangesRotting(grid3));
        // Expected output: 0

        // Test case 4
        int[][] grid4 = {{1}};
        System.out.println("Test case 4: " + solution.orangesRotting(grid4));
        // Expected output: -1

        // Test case 5
        int[][] grid5 = {{2,2,2,1,1}};
        System.out.println("Test case 5: " + solution.orangesRotting(grid5));
        // Expected output: 2
    }
}

class Solution {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        
        // Step 1: Initialize the queue with rotten oranges and count fresh oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        
        // Step 2: BFS to rot oranges
        int minutes = 0;
        while (!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] dir : DIRECTIONS) {
                    int newRow = cell[0] + dir[0];
                    int newCol = cell[1] + dir[1];
                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        freshOranges--;
                    }
                }
            }
            minutes++;
        }
        
        // Step 3: Check if all oranges are rotten
        return freshOranges == 0 ? minutes : -1;
    }
}

