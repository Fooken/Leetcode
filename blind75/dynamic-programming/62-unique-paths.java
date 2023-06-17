class Solution {
    // The robot can only move either down or right at any point in time.
    // DP: f[i][j] = f[i - 1][j] + f[i][j - 1];
    // f[i][j] the number of possible unique paths
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        f[0][0] = 1;

        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // initial
                if (i == 0 || j == 0) {
                    f[i][j] = 1; //第一行和第一列只有一种方式到达
                    continue;
                }
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }

        return f[m - 1][n - 1];
    }
}
