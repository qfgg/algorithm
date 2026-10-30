import java.util.*;


class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        double[][] dp = new double[n][n], pre = new double[n][n], tmp;
        int i, j, l, m, nr, nc;
        for (i = 0; i < n; i++) {
            Arrays.fill(pre[i], 1);
        }
        int[][] dir = new int[][]{
                {1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}
        };
        for (m = 0; m < k; m++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    dp[i][j] = 0;
                    for (l = 0; l < 8; l++) {
                       nr = i + dir[l][0];
                       nc = j + dir[l][1];
                       if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                           dp[i][j] += pre[nr][nc] / 8;
                       }
                    }
                }
            }
            if (m == k - 1) {
                break;
            }
            tmp = pre;
            pre = dp;
            dp = tmp;
        }
        return dp[row][column];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int n = 3, k = 2, row = 0, column = 0;
        double ret = slt.knightProbability(n, k, row, column);
        System.out.println(ret);
    }
}
