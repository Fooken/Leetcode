import java.util.*;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                char num = board[i][j];

                if (num != '.') {
                    if (!set.add(num + "in row: " + i) || !set.add(num + "in col: " + j) || !set.add(num + "in row: " + i / 3 + "in col: " + j / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
