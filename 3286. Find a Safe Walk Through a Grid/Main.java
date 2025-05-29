import java.util.*;


class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.getFirst().size(), r, c, nr, nc, k, tmp;
        boolean[][][] visited = new boolean[m][n][health + 1];
        Queue<int[]> q = new LinkedList<>();
        tmp = grid.getFirst().getFirst();
        q.add(new int[]{0, 0, health - tmp});
        visited[0][0][health - tmp] = true;
        int[] dir = new int[]{0, -1, 0, 1, 0};
        int[] cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            for (k = 0; k < 4; k++) {
                nr = r + dir[k];
                nc = c + dir[k + 1];
                if (
                        nr >= 0 && nr < m && nc >= 0 && nc < n &&
                                cur[2] - grid.get(nr).get(nc) > 0 &&
                                !visited[nr][nc][cur[2] - grid.get(nr).get(nc)]
                ) {
                    if (nr == m - 1 && nc == n - 1) {
                        return true;
                    }
                    visited[nr][nc][cur[2] - grid.get(nr).get(nc)] = true;
                    q.add(new int[]{nr, nc, cur[2] - grid.get(nr).get(nc)});
                }
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(List.of(0,1,1,0,0,0)));
        grid.add(new ArrayList<>(List.of(1,0,1,0,0,0)));
        grid.add(new ArrayList<>(List.of(0,1,1,1,0,1)));
        grid.add(new ArrayList<>(List.of(0,0,1,0,1,0)));
        boolean res = s.findSafeWalk(grid, 3);
        System.out.println(res);
    }
}
