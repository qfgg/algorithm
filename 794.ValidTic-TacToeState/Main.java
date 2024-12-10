import java.util.*;

class Solution {
    public boolean validTicTacToe(String[] board) {
        int i, j, idx;
        int[] cnt = new int[2];
        int[][] cnt8 = new int[2][8];
        boolean[][] win = new boolean[2][3];
        boolean[] w = new boolean[2];
        char c;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                c = board[i].charAt(j);
                idx = c == 'X' ? 0 : c == 'O' ? 1 : -1;
                if (idx != -1) {
                    cnt[idx]++;
                    cnt8[idx][i]++;
                    cnt8[idx][j + 3]++;
                    if (i == j) {
                        cnt8[idx][6]++;
                    }
                    if (i + j == 2) {
                        cnt8[idx][7]++;
                    }
                    if (cnt8[idx][i] == 3) {
                        if (win[idx][0] || win[1 - idx][0] ||  win[1 - idx][1] ||  win[1 - idx][2]) {
                            return false;
                        }
                        win[idx][0] = true;
                        w[idx] = true;
                    }
                    if (cnt8[idx][j + 3] == 3) {
                        if (win[idx][1] || win[1 - idx][0] ||  win[1 - idx][1] ||  win[1 - idx][2]) {
                            return false;
                        }
                        win[idx][1] = true;
                        w[idx] = true;
                    }
                    if (cnt8[idx][6] == 3 || cnt8[idx][7] == 3) {
                        if (win[1 - idx][0] ||  win[1 - idx][1] ||  win[1 - idx][2]) {
                            return false;
                        }
                        win[idx][2] = true;
                        w[idx] = true;
                    }
                }
            }
        }
        if ((cnt[0] != cnt[1] && cnt[0] - cnt[1] != 1) ||
                (w[0] && cnt[0] - cnt[1] != 1) ||
                (w[1] && cnt[0] != cnt[1])
        ) {
            return false;
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] board = new String[]{"XXX","XOO","OO "};
        boolean res = s.validTicTacToe(board);
        System.out.println(res);
    }
}
