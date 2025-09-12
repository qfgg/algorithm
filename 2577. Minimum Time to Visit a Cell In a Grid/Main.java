import java.util.*;


class Solution {
    public int minimumTime(int[][] grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }
        int m = grid.length, n = grid[0].length, i, nr, nc, t;
        int[] dir = new int[]{0, 1, 0, -1, 0}, cur;
        int[][] minTime = new int[m][n];
        for (i = 0; i < m; i++) {
            Arrays.fill(minTime[i], Integer.MAX_VALUE);
        }
        minTime[0][0] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        while (!q.isEmpty()) {
            do {
                cur = q.poll();
            } while (!q.isEmpty() && cur[2] != minTime[cur[0]][cur[1]]);
            if (cur[2] != minTime[cur[0]][cur[1]]) {
                break;
            }
            for (i = 0; i < 4; i++) {
                nr = cur[0] + dir[i];
                nc = cur[1] + dir[i + 1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    t = Math.max(1, grid[nr][nc] - cur[2]);
                    if (t > 1) {
                        t = t % 2 == 1 ? t : t + 1;
                    }
                    if (cur[2] + t < minTime[nr][nc]) {
                        minTime[nr][nc] = cur[2] + t;
                        q.add(new int[]{nr, nc, cur[2] + t});
                    }
                }
            }
        }
        return minTime[m - 1][n - 1] == Integer.MAX_VALUE ? -1 : minTime[m - 1][n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = {
                {0,1,99},
                {3,99,99},
                {4,5,6},
        };
        int res = s.minimumTime(grid);
        System.out.println(res);
    }
}
