import java.util.*;


class Solution {
    public int maxDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length, i, j, k, ny, nx, max = -1;
        int[][] dist = new int[m][n];
        int[] dir = new int[]{0, -1, 0, 1, 0}, cur;
        Queue<int[]> q = new LinkedList<>();
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dist[i][j] = -1;
                    for (k = 0; k < 4; k++) {
                        ny = i + dir[k];
                        nx = j + dir[k + 1];
                        if (ny >= 0 && ny < m && nx >= 0 && nx <n && grid[ny][nx] == 0) {
                            dist[ny][nx] = 1;
                            q.add(new int[]{ny, nx, 1});
                            max = 1;
                        }
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            cur = q.poll();
            for (k = 0; k < 4; k++) {
                ny = cur[0] + dir[k];
                nx = cur[1] + dir[k + 1];
                if (ny >= 0 && ny < m && nx >= 0 && nx <n && dist[ny][nx] == 0 && grid[ny][nx] == 0) {
                    dist[ny][nx] = cur[2] + 1;
                    q.add(new int[]{ny, nx, dist[ny][nx]});
                    max = Math.max(max, dist[ny][nx]);
                }
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = new int[][]{{1,0,1},{0,0,0},{1,0,1}};
        int res = s.maxDistance(grid);
        System.out.println(res);
    }
}
