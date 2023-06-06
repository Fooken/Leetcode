class Solution {
    // backtracking
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (word.charAt(0) == board[i][j] && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int idx) {
        if (idx == word.length()) {
            return true;
        }

        if (r < 0 || r >= board.length || 
            c < 0 || c >= board[0].length || 
            board[r][c] == '#' || 
            word.charAt(idx) != board[r][c]) {
            return false;
        }

        char ch = board[r][c];

        // visiting
        board[r][c] = '#';
        boolean res = false;

        int[][] dires = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir: dires) {
            int x = dir[0] + r;
            int y = dir[1] + c;
            res = dfs(board, word, x , y, idx + 1);
            if (res) break;
        }

        // boolean res = dfs(board, word, r - 1, c, idx + 1) 
        // || dfs(board, word, r + 1, c, idx + 1) 
        // || dfs(board, word, r, c - 1, idx + 1) 
        // || dfs(board, word, r, c + 1, idx + 1);

        // backtracking
        board[r][c] = ch;

        return res;
    }
}
