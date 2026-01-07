import java.util.*;


class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length, i, j, inc = 0;
        int[] rmax = new int[m], cmax = new int[n];
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                rmax[i] = Math.max(rmax[i], grid[i][j]);
                cmax[j] = Math.max(cmax[j], grid[i][j]);
            }
        }
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                inc += Math.min(rmax[i], cmax[j]) - grid[i][j];
            }
        }
        return inc;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = new int[][]{
                {3,0,8,4},
                {2,4,5,7},
                {9,2,6,3},
                {0,3,1,0},
        };
        int res = s.maxIncreaseKeepingSkyline(grid);
        System.out.println(res);
    }
}
