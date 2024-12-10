import java.util.*;


public class Main {
    public static void dfs(int[][] heights, int[][] memo, int r, int c, int rn, int cn) {
        int dist, pathMax;
        if (r > 0) {
            dist = Math.abs(heights[r - 1][c] - heights[r][c]);
            pathMax = Math.max(memo[r][c], dist);
            if (pathMax < memo[r - 1][c]) {
                memo[r - 1][c] = pathMax;
                dfs(heights, memo, r - 1, c, rn, cn);
            }
        }
        if (c > 0) {
            dist = Math.abs(heights[r][c - 1] - heights[r][c]);
            pathMax = Math.max(memo[r][c], dist);
            if (pathMax < memo[r][c - 1]) {
                memo[r][c - 1] = pathMax;
                dfs(heights, memo, r, c - 1, rn, cn);
            }
        }
        if (r < rn - 1) {
            dist = Math.abs(heights[r + 1][c] - heights[r][c]);
            pathMax = Math.max(memo[r][c], dist);
            if (pathMax < memo[r + 1][c]) {
                memo[r + 1][c] = pathMax;
                dfs(heights, memo, r + 1, c, rn, cn);
            }
        }
        if (c < cn - 1) {
            dist = Math.abs(heights[r][c + 1] - heights[r][c]);
            pathMax = Math.max(memo[r][c], dist);
            if (pathMax < memo[r][c + 1]) {
                memo[r][c + 1] = pathMax;
                dfs(heights, memo, r, c + 1, rn, cn);
            }
        }
    }
    public static int minimumEffortPath(int[][] heights) {
        int rn = heights.length, cn = heights[0].length;
        int[][] memo = new int[rn][cn];
        for (int[] row: memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        memo[0][0] = 0;
        dfs(heights, memo, 0, 0, rn, cn);
        return memo[rn - 1][cn - 1];
    }
    public static void main(String[] args) {
        int[][] heights = new int[][]{{1,10,6,7,9,10,4,9}};
        int res = minimumEffortPath(heights);
        System.out.println(res);
    }
}
