import java.util.*;


class Solution {
    private void computeDistence(int[][] gd, int[][] mins, List<Integer> tf, int rn, int cn) {
        for (int[] row : mins) {
            Arrays.fill(row, 400);
        }
        int y, x, ny, nx, k, dis = 1, size, pos;
        int[] dir = new int[]{0, -1, 0, 1, 0};
        Queue<Integer> q = new LinkedList<>();
        for (Integer cur : tf) {
            q.add(cur);
            y = cur / 1000;
            x = cur % 1000;
            mins[y][x] = 0;
        }
        size = q.size();
        dis = 1;
        while (size > 0) {
            pos = q.poll();
            size--;
            y = pos / 1000;
            x = pos % 1000;
            for (k = 0; k < 4; k++) {
                ny = y + dir[k];
                nx = x + dir[k + 1];
                if (ny >= 0 && ny < rn && nx >= 0 && nx < cn && gd[ny][nx] == 0 && mins[ny][nx] > dis) {
                    q.add(ny * 1000 + nx);
                    mins[ny][nx] = dis;
                }
            }
            if (size == 0) {
                size = q.size();
                dis++;
            }
        }
    }
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        List<Integer> row, tf = new ArrayList<>();
        int rn = grid.size(), cn = grid.get(0).size(), i, j, k;
        int[][] gd = new int[rn][cn];
        int[][] mins = new int[rn][cn];
        for (i = 0; i < rn; i++) {
            row = grid.get(i);
            for (j = 0; j < cn; j++) {
                gd[i][j] = row.get(j);
                if (gd[i][j] == 1) {
                    tf.add(i * 1000 + j);
                }
            }
        }
        computeDistence(gd, mins, tf, rn, cn);
        int y, x, ny, nx, nextMin;
        int[] dir = new int[]{0, -1, 0, 1, 0}, cur;
        int[][] pathMinMaxs = new int[rn][cn];
        boolean[][] visited = new boolean[rn][cn];
        pathMinMaxs[0][0] = mins[0][0];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> pathMinMaxs[b[0]][b[1]] - pathMinMaxs[a[0]][a[1]]);
        q.add(new int[]{0, 0, pathMinMaxs[0][0]});
        while (!q.isEmpty()) {
            cur = q.poll();
            if (cur[2] != pathMinMaxs[cur[0]][cur[1]]) {
                continue;
            }
            y = cur[0];
            x = cur[1];
            visited[y][x] = true;
            for (k = 0; k < 4; k++) {
                ny = y + dir[k];
                nx = x + dir[k + 1];
                if (ny >= 0 && ny < rn && nx >= 0 && nx < cn && !visited[ny][nx]) {
                    nextMin = Math.min(pathMinMaxs[y][x], mins[ny][nx]);
                    if (nextMin > pathMinMaxs[ny][nx]) {
                        pathMinMaxs[ny][nx] = nextMin;
                        q.add(new int[]{ny, nx, pathMinMaxs[ny][nx]});
                    }
                }
            }
        }
        return pathMinMaxs[rn - 1][cn - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(List.of(1,1,1)));
        grid.add(new ArrayList<>(List.of(0,1,1)));
        grid.add(new ArrayList<>(List.of(0,0,0)));
        int res = s.maximumSafenessFactor(grid);
        System.out.println(res);
    }
}
