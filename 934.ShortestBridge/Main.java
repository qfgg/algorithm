import java.util.*;

class Solution {
    List<Integer> getBorder(int[][] grid, int r, int c, int y, int x, int mark) {
        List<Integer> b = new ArrayList<>();
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        int i, cur, ny, nx;
        boolean isNextToZero;
        Queue<Integer> q = new LinkedList<>();
        grid[y][x] = mark;
        q.add(y * 100 + x);
        while (!q.isEmpty()) {
            cur = q.poll();
            isNextToZero = false;
            for (i = 0; i < 4; i++) {
                ny = cur / 100 + dir[i];
                nx = cur % 100 + dir[i + 1];
                if (ny >= 0 && ny < r && nx >= 0 && nx < c) {
                    if (grid[ny][nx] == 1) {
                        grid[ny][nx] = mark;
                        q.add(ny * 100 + nx);
                    } else if (grid[ny][nx] == 0) {
                        isNextToZero = true;
                    }
                }
            }
            if (isNextToZero) {
                b.add(cur);
            }
        }
        return b;
    }

    public int findBridge(int[][] grid, int r, int c, List<Integer> b1) {
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        int i, cur, ny, nx, size, dist = 0;
        Queue<Integer> q = new LinkedList<>();
        q.addAll(b1);
        size = q.size();
        while (size != 0) {
            cur = q.poll();
            size--;
            for (i = 0; i < 4; i++) {
                ny = cur / 100 + dir[i];
                nx = cur % 100 + dir[i + 1];
                if (ny >= 0 && ny < r && nx >= 0 && nx < c && grid[ny][nx] != 2) {
                    if (grid[ny][nx] == 3) {
                        return dist;
                    }
                    grid[ny][nx] = 2;
                    q.add(ny * 100 + nx);
                }
            }
            if (size == 0) {
                dist++;
                size = q.size();
            }
        }
        return dist;
    }
    public int shortestBridge(int[][] grid) {
        int r = grid.length, c = grid[0].length, i, j, k = 0;
        List<Integer>[] b = new List[2];
        for (i = 0; i < r; i++) {
            for (j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    b[k] = getBorder(grid, r, c, i, j, k + 2);
                    k++;
                }
            }
        }
        return findBridge(grid, r, c, b[0]);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = new int[][]{
                {0,1,0},
                {0,0,0},
                {0,0,1},
        };
        int res = s.shortestBridge(grid);
        System.out.println(res);
    }
}
