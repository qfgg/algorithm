import java.util.*;


class Solution {
    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length, c, r, max = 0;
        int[][] maxMoves = new int[m][n];
        for (c = n - 2; c >= 0; c--) {
            for (r = 0; r < m; r++) {
                if (r > 0 && grid[r - 1][c + 1] > grid[r][c]) {
                    maxMoves[r][c] = Math.max(maxMoves[r][c], maxMoves[r - 1][c + 1] + 1);
                }
                if (grid[r][c + 1] > grid[r][c]) {
                    maxMoves[r][c] = Math.max(maxMoves[r][c], maxMoves[r][c + 1] + 1);
                }
                if (r < m - 1 && grid[r + 1][c + 1] > grid[r][c]) {
                    maxMoves[r][c] = Math.max(maxMoves[r][c], maxMoves[r + 1][c + 1] + 1);
                }
                if (c == 0) {
                    max = Math.max(max, maxMoves[r][c]);
                }
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = new int[][]{
                {2,4,3,5},
                {5,4,9,3},
                {3,4,2,11},
                {10,9,13,15}
        };
        System.out.println(s.maxMoves(grid));
    }
}
