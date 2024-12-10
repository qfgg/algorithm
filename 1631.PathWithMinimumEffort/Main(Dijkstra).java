import java.util.*;


public class Main {
    public static int minimumEffortPath(int[][] heights) {
        int rn = heights.length, cn = heights[0].length;
        int[] dir = new int[]{-1,0,1,0,-1};
        int[][] dist = new int[rn][cn];
        boolean[][] seen = new boolean[rn][cn];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0,0,0});
        int[] cur;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            int r = cur[0], c = cur[1], d = cur[2];
            if (seen[r][c]) { // remove deprecated value
                continue;
            }
            if (r == rn - 1 && c == cn - 1) {
                break;
            }
            int pos, nr, nc;
            seen[r][c] = true;
            for (pos = 0; pos < 4; pos++) {
                nr = r + dir[pos];
                nc = c + dir[pos + 1];
                if (nr >= 0 && nc >= 0 && nr < rn && nc < cn && Math.max(d, Math.abs(heights[r][c] - heights[nr][nc])) < dist[nr][nc]) {
                    dist[nr][nc] = Math.max(d, Math.abs(heights[r][c] - heights[nr][nc]));
                    pq.add(new int[]{nr, nc, dist[nr][nc]});
                }
            }
        }
        return dist[rn - 1][cn - 1];
    }
    public static void main(String[] args) {
        int[][] heights = new int[100][100];
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                heights[i][j] = r.nextInt(1000000) + 1;
            }
        }
        int res = minimumEffortPath(heights);
        System.out.println(res);
    }
}
