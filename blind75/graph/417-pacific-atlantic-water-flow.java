class Solution {
    // BFS
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        List<List<Integer>> res = new ArrayList<>();

        if (heights == null || m == 0 || n == 0) {
            return res;
        }

        boolean[][] po = new boolean[m][n];
        boolean[][] ao = new boolean[m][n];
        Queue<int[]> pQ = new LinkedList<>();
        Queue<int[]> aQ = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            pQ.offer(new int[]{i, 0});
            aQ.offer(new int[]{i, n - 1});
            po[i][0] = true;
            ao[i][n - 1] = true;
        }

        for (int j = 0; j < n; j++) {
            pQ.offer(new int[]{0, j});
            aQ.offer(new int[]{m - 1, j});
            po[0][j] = true;
            ao[m - 1][j] = true;
        }
        bfs(heights, po, pQ);
        bfs(heights, ao, aQ);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (po[i][j] && ao[i][j]) {
                    List<Integer> list = new ArrayList<>(Arrays.asList(i, j));
                    res.add(list);
                }
            }
        }
        return res;
    }

    private void bfs(int[][] heights, boolean[][] visited, Queue<int[]> queue) {
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir: dirs) {
                int x = dir[0] + cur[0];
                int y = dir[1] + cur[1];

                if (x < 0 || x >= heights.length || 
                    y < 0 || y >= heights[0].length || 
                    visited[x][y] || 
                    heights[x][y] < heights[cur[0]][cur[1]]) {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }


    // DFS
    public List<List<Integer>> pacificAtlantic1(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        List<List<Integer>> res = new ArrayList<>();

        if (heights == null || m == 0 || n == 0) {
            return res;
        }

        boolean[][] po = new boolean[m][n];
        boolean[][] ao = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(heights, po, Integer.MIN_VALUE, i, 0);
            dfs(heights, ao, Integer.MIN_VALUE, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(heights, po, Integer.MIN_VALUE, 0, j);
            dfs(heights, ao, Integer.MIN_VALUE, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (po[i][j] && ao[i][j]) {
                    List<Integer> list = new ArrayList<>(Arrays.asList(i, j));
                    res.add(list);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, boolean[][] visited, int height, int i, int j) {
        if (i < 0 || i >= heights.length || j < 0 || j >= heights[0].length 
        || heights[i][j] < height 
        || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] dir: dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            dfs(heights, visited, heights[i][j], x, y);
        }
    }
}
