import java.util.*;


class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length, i, j;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (j = 1; j < n; j++) {
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
        }
        for (i = 1; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        for (i = 1; i < m; i++) {
            for (j = 1; j < n; j++) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] obstacleGrid = new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0},
        };
        int res = s.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(res);
    }
}
