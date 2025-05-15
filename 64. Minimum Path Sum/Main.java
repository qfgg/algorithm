import java.util.*;


class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length, i, j;
        int[][] min = new int[m][n];
        min[0][0] = grid[0][0];
        for (j = 1; j < n; j++) {
            min[0][j] = min[0][j - 1] + grid[0][j];
        }
        for (i = 1; i < m; i++) {
            min[i][0] = min[i - 1][0] + grid[i][0];
        }
        for (i = 1; i < m; i++) {
            for (j = 1; j < n; j++) {
                min[i][j] = Math.min(min[i][j - 1], min[i - 1][j]) + grid[i][j];
            }
        }
        return min[m - 1][n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1},
        };
        int res = s.minPathSum(grid);
        System.out.println(res);
    }
}
