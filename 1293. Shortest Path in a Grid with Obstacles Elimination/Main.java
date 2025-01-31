import java.util.*;

class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int i, y = 0, x = 0, ny, nx, step = 0, size, curk, nextk;
        int[] offset = new int[]{1, 0, -1, 0, 1};
        Queue<List<Integer>> points = new LinkedList<>();
        Map<List<Integer>, Integer> kmap = new HashMap<>();
        List<Integer> start = new ArrayList<>(Arrays.asList(0, 0));
        List<Integer> cur, next;
        points.add(start);
        kmap.put(start, 0);
        size = points.size();
        while (!points.isEmpty()) {
            cur = points.poll();
            size--;
            y = cur.get(0);
            x = cur.get(1);
            if (y == m - 1 && x == n - 1) {
                break;
            }
            curk = kmap.get(cur);
            for (i = 0; i < 4; i++) {
                ny = y + offset[i];
                nx = x + offset[i + 1];
                if (ny >= 0 && ny < m && nx >= 0 && nx < n) {
                    nextk = curk + (grid[ny][nx] == 1 ? 1 : 0);
                    if (nextk <= k) {
                        next = new ArrayList<>(Arrays.asList(ny, nx));
                        if (!kmap.containsKey(next) || nextk < kmap.get(next)) {
                            points.add(next);
                            kmap.put(next, nextk);
                        }
                    }
                }
            }
            if (size == 0) {
                step++;
                size = points.size();
            }
        }
        if (y != m - 1 || x != n - 1) {
            return -1;
        }
        return step;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = new int[][]{
                {0,0,0},
                {1,1,0},
                {0,0,0},
                {0,1,1},
                {0,0,0}
        };
        int k = 1;
        int res = s.shortestPath(grid, k);
        System.out.println(res);
    }
}
