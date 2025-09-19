import java.util.*;


class Solution {
    public void gameOfLife(int[][] board) { //0 -> 4,5;1 -> 2,3
        int m = board.length, n = board[0].length, i, j, k, ny, nx, liveCnt;
        int[][] dir = new int[][]{
                {0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}
        };
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                liveCnt = 0;
                for (k = 0; k < 8; k++) {
                    ny = i + dir[k][0];
                    nx = j + dir[k][1];
                    if (ny >= 0 && ny < m && nx >= 0 && nx < n) {
                        if (board[ny][nx] == 1 || board[ny][nx] == 2 || board[ny][nx] == 3) {
                            liveCnt++;
                        }
                    }
                }
                if (board[i][j] == 1) {
                    if (liveCnt < 2 || liveCnt > 3) {
                        board[i][j] = 2;
                    } else {
                        board[i][j] = 3;
                    }
                } else {
                    if (liveCnt == 3) {
                        board[i][j] = 5;
                    } else {
                        board[i][j] = 4;
                    }
                }
            }
        }
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (board[i][j] == 2 || board[i][j] == 4) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = 1;
                }
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals = new int[][]{
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0},
        };
        s.gameOfLife(intervals);
        System.out.println(Arrays.deepToString(intervals));
    }
}
