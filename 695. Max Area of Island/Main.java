import java.util.*;


class Solution {
    private void getArea(int[][] grid, int y, int x, int rn, int cn, boolean[][] visited, int[] area) {
        int[] dir = new int[]{0, -1, 0, 1, 0};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;
        int[] cur;
        int ny, nx;
        while (!q.isEmpty()) {
            cur = q.poll();
            area[0]++;
            for (int i = 0; i < 4; i++) {
                ny = cur[0] + dir[i];
                nx = cur[1] + dir[i + 1];
                if (ny >= 0 && ny < rn && nx >= 0 && nx < cn && grid[ny][nx] == 1 && !visited[ny][nx]) {
                    q.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
    }
    public int maxAreaOfIsland(int[][] grid) {
        int i, j, rn = grid.length, cn = grid[0].length, max = 0;
        boolean[][] visited = new boolean[rn][cn];
        int[] area = new int[1];
        for (i = 0; i < rn; i++) {
            for (j = 0; j < cn; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    area[0] = 0;
                    getArea(grid, i, j, rn, cn, visited, area);
                    max = Math.max(max, area[0]);
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
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1},
        };
        int res = s.maxAreaOfIsland(grid);
        System.out.println(res);
    }
}
