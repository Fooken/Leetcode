class Solution {
    // BFS: T: O(M×N) S: O(min(M,N))
    public int numIslands1(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    queue.offer(new int[]{i, j});
                    count++;
                    // visited
                    grid[i][j] = '0';

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int[] dir: dirs) {
                            int x = dir[0] + cur[0];
                            int y = dir[1] + cur[1];
                            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') {
                                continue;
                            }
                            // visited
                            grid[x][y] = '0';
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return count;
    }

    // DFS
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        // visited
        grid[i][j] = '0';
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        for (int[] dir: dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            dfs(grid, x, y);
        }

    }

















}

