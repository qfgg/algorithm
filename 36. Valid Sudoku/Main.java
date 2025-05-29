import java.util.*;


class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[][] boxes = new int[3][3];
        int idx, next;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    idx = board[i][j] - '1';
                    next = rows[i] | (1 << idx);
                    if (next == rows[i]) {
                        return false;
                    }
                    rows[i] = next;
                    next = cols[j] | (1 << idx);
                    if (next == cols[j]) {
                        return false;
                    }
                    cols[j] = next;
                    next = boxes[i / 3][j / 3] | (1 << idx);
                    if (next == boxes[i / 3][j / 3]) {
                        return false;
                    }
                    boxes[i / 3][j / 3] = next;
                }
            }
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        boolean res = s.isValidSudoku(board);
        System.out.println(res);
    }
}
