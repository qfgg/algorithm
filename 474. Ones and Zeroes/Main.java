import java.util.*;


class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length, i, j, k;
        int[][] cnt = new int[l][2];
        for (i = 0; i < l; i++) {
            for (j = strs[i].length() - 1; j >= 0; j--) {
                cnt[i][strs[i].charAt(j) - '0']++;
            }
        }
        int[][][] dp = new int[l][m + 1][n + 1];
        for (j = 0; j <= m; j++) {
            for (k = 0; k <= n; k++) {
                if (cnt[0][0] <= j && cnt[0][1] <= k) {
                    dp[0][j][k] = 1;
                }
            }
        }
        for (i = 1; i < l; i++) {
            for (j = 0; j <= m; j++) {
                for (k = 0; k <= n; k++) {
                    if (cnt[i][0] <= j && cnt[i][1] <= k) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], 1 + dp[i - 1][j - cnt[i][0]][k - cnt[i][1]]);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[l - 1][m][n];
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      String[] strs = {"10","0001","111001","1","0"};
      int m = 5, n = 3;
      int res = s.findMaxForm(strs, m, n);
      System.out.println(res);
  }
}

