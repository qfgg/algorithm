import java.util.*;


class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        if (m == 1 && n == 1) {
            return mat;
        }
        int k, r, c, nr, nc;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        int[][] dist = new int[m][n];
        int[] dir = new int[]{0, -1, 0, 1, 0}, cur;
        for (r = 0; r < m; r++) {
            for (c = 0; c < n; c++) {
                if (mat[r][c] == 0) {
                    visited[r][c] = true;
                    for (k = 0; k < 4; k++) {
                        nr = r + dir[k];
                        nc = c + dir[k + 1];
                        if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && mat[nr][nc] == 1) {
                            visited[nr][nc] = true;
                            dist[nr][nc] = 1;
                            q.add(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            for (k = 0; k < 4; k++) {
                nr = r + dir[k];
                nc = c + dir[k + 1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && mat[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return dist;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] mat = new int[][]{
                {0,0,0},
                {0,1,0},
                {1,1,1},
        };
        int[][] res = s.updateMatrix(mat);
        System.out.println(Arrays.deepToString(res));
    }
}
