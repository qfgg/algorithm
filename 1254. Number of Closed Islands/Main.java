import java.util.*;


class Solution {
    void mark(int[][] grid, int m, int n, int r, int c, int val) {
        Queue<int[]> q = new LinkedList<>();
        grid[r][c] = val;
        q.add(new int[]{r, c});
        int[] dir = new int[]{0, -1, 0, 1, 0}, cur;
        int k, nr, nc;
        while (!q.isEmpty()) {
            cur = q.poll();
            for (k = 0; k < 4; k++) {
                nr = cur[0] + dir[k];
                nc = cur[1] + dir[k + 1];
                if (nr > 0 && nr < m - 1 && nc > 0 && nc < n - 1 && grid[nr][nc] == 0) {
                    grid[nr][nc] = val;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length, i, j, cnt = 0;
        for (i = 0; i < n; i++) {
            if (grid[0][i] == 0) {
                mark(grid, m, n, 0, i, 1);
            }
            if (grid[m - 1][i] == 0) {
                mark(grid, m, n, m - 1, i, 1);
            }
        }
        for (i = 1; i < m - 1; i++) {
            if (grid[i][0] == 0) {
                mark(grid, m, n, i, 0, 1);
            }
            if (grid[i][n - 1] == 0) {
                mark(grid, m, n, i, n - 1, 1);
            }
        }
        for (i = 1; i < m - 1; i++) {
            for (j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    cnt++;
                    mark(grid, m, n, i, j, 2);
                }
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = new int[][]{
                {1,1,1,1,1,1,1,0},
                {1,0,0,0,0,1,1,0},
                {1,0,1,0,1,1,1,0},
                {1,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,0}
        };
        int res = s.closedIsland(grid);
        System.out.println(res);
    }
}
