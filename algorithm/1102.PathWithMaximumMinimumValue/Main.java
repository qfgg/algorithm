import java.util.*;

class Solution {
    public int maximumMinimumPath(int[][] A) {
        int[] dir = new int[]{-1,0,1,0,-1}, cur;
        int rn = A.length, cn = A[0].length, i, nx, ny, curMin;
        boolean[][] done = new boolean[rn][cn];
        int[][] dist = new int[rn][cn];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        dist[0][0] = A[0][0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.add(new int[]{0, 0, dist[0][0]});
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (cur[2] != dist[cur[0]][cur[1]]) {
                continue;
            }
            done[cur[0]][cur[1]] = true;
            if (cur[0] == rn - 1 && cur[1] == cn - 1) {
                break;
            }
            for (i = 0; i < 4; i++) {
                ny = cur[0] + dir[i];
                nx = cur[1] + dir[i + 1];
                if (nx >= 0 && nx < cn && ny >= 0 && ny < rn && !done[ny][nx]) {
                    curMin = Math.min(cur[2], A[ny][nx]);
                   if (curMin > dist[ny][nx]) {
                       dist[ny][nx] = curMin;
                       pq.add(new int[]{ny, nx, curMin});
                   }
                }
            }
        }
        return dist[rn - 1][cn - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] A = new int[][]{
                {5,4,5},
                {1,2,6},
                {7,4,6}
        };
        int res = s.maximumMinimumPath(A);
        int[][] B = new int[][]{
                {2,2,1,2,2,2},
                {1,2,2,2,1,2}
        };
        int res1 = s.maximumMinimumPath(B);
        int[][] C = new int[][]{
                {3,4,6,3,4},
                {0,2,1,1,7},
                {8,8,3,2,7},
                {3,2,4,9,8},
                {4,1,2,0,0},
                {4,6,5,4,3}
        };
        int res2 = s.maximumMinimumPath(C);
        System.out.println(res);
        System.out.println(res1);
        System.out.println(res2);
    }
}
