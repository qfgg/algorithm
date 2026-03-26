import java.util.*;


class Solution {
    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length, i, j;
        int[] res = new int[n];
        for (i = 0; i < n; i++) {
            res[i] = i;
        }
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (res[j] == -1) {
                    continue;
                }
                if (grid[i][res[j]] == 1) {
                    if (res[j] == n - 1 || grid[i][res[j] + 1] == -1) {
                        res[j] = -1;
                    } else {
                        res[j]++;
                    }
                } else {
                    if (res[j] == 0 || grid[i][res[j] - 1] == 1) {
                        res[j] = -1;
                    } else {
                        res[j]--;
                    }
                }
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = new int[][]{
                {1,1,1,-1,-1},
                {1,1,1,-1,-1},
                {-1,-1,-1,1,1},
                {1,1,1,1,-1},
                {-1,-1,-1,-1,-1}
        };
        int[] res = s.findBall(grid);
        System.out.println(Arrays.toString(res));
    }
}
