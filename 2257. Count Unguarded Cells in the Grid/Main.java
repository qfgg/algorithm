import java.util.*;


class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        int i, k, gn = guards.length, wn = walls.length, count = m * n - gn - wn, curr, curc, nr, nc;
        for (i = 0; i < wn; i++) {
            grid[walls[i][0]][walls[i][1]] = -1;
        }
        int[] dir = new int[]{0, -1, 0, 1, 0};
        Queue<int[]> q = new LinkedList<>();
        for (i = 0; i < gn; i++) {
            grid[guards[i][0]][guards[i][1]] = -2;
            q.add(new int[]{guards[i][0], guards[i][1]});
        }
        int[] pos;
        while (!q.isEmpty()) {
            pos = q.poll();
            curr = pos[0];
            curc = pos[1];
            for (k = 0; k < 4; k++) {
                if (grid[curr][curc] == -2 || (grid[curr][curc] & (1 << k)) > 0) {
                    grid[curr][curc] = grid[curr][curc] & (~(1 << k));
                    nr = curr + dir[k];
                    nc = curc + dir[k + 1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] >= 0) {
                        if (grid[nr][nc] == 0) {
                            count--;
                        }
                        grid[nr][nc] = grid[nr][nc] | 16 | (1 << k);
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int m = 6, n = 10;
        int[][] guards = new int[][]{{0,6},{2,2},{2,5},{1,2},{4,9},{2,9},{5,6},{4,6}};
        int[][] walls = {{1,5}};
        int res = s.countUnguarded(m, n, guards, walls);
        System.out.println(res);
    }
}
