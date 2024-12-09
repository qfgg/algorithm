import java.util.*;


public class Main {
    public static int minimumEffortPath(int[][] heights) {
        int rn = heights.length, cn = heights[0].length;
        int[][] max = new int[rn][cn];
        for (int[] row: max) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        max[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> -a[2] + b[2]);
        pq.add(new int[]{0,0,0});
        int[] cur;
        int nextMax;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (max[cur[0]][cur[1]] != max[cur[0]][cur[1]]) { // remove deprecated value
                continue;
            }
            max[cur[0]][cur[1]] = -max[cur[0]][cur[1]];
            if (cur[0] == rn - 1 && cur[1] == cn - 1) {
                break;
            }
            if (cur[0] > 0) {
                nextMax = Math.max(max[cur[0]][cur[1]], Math.abs(heights[cur[0]][cur[1]] - heights[cur[0] - 1][cur[1]]));
                if (max[cur[0] - 1][cur[1]] == Integer.MAX_VALUE ||
                        (max[cur[0] - 1][cur[1]] < 0 && nextMax < -max[cur[0] - 1][cur[1]])) {
                    max[cur[0] - 1][cur[1]] = -nextMax;
                    pq.add(new int[]{cur[0] - 1, cur[1], -nextMax});
                }
            }
            if (cur[1] > 0) {
                nextMax = Math.max(max[cur[0]][cur[1]], Math.abs(heights[cur[0]][cur[1]] - heights[cur[0]][cur[1] - 1]));
                if (max[cur[0]][cur[1] - 1] == Integer.MAX_VALUE ||
                        (max[cur[0]][cur[1] - 1] < 0 && nextMax < -max[cur[0]][cur[1] - 1])) {
                    max[cur[0]][cur[1] - 1] = -nextMax;
                    pq.add(new int[]{cur[0], cur[1] - 1, -nextMax});
                }
            }
            if (cur[0] < rn - 1) {
                nextMax = Math.max(max[cur[0]][cur[1]], Math.abs(heights[cur[0]][cur[1]] - heights[cur[0] + 1][cur[1]]));
                if (max[cur[0] + 1][cur[1]] == Integer.MAX_VALUE ||
                        (max[cur[0] + 1][cur[1]] < 0 && nextMax < -max[cur[0] + 1][cur[1]])) {
                    max[cur[0] + 1][cur[1]] = -nextMax;
                    pq.add(new int[]{cur[0] + 1, cur[1], -nextMax});
                }
            }
            if (cur[1] < cn - 1) {
                nextMax = Math.max(max[cur[0]][cur[1]], Math.abs(heights[cur[0]][cur[1]] - heights[cur[0]][cur[1] + 1]));
                if (max[cur[0]][cur[1] + 1] == Integer.MAX_VALUE ||
                        (max[cur[0]][cur[1] + 1] < 0 && nextMax < -max[cur[0]][cur[1] + 1])) {
                    max[cur[0]][cur[1] + 1] = -nextMax;
                    pq.add(new int[]{cur[0], cur[1] + 1, -nextMax});
                }
            }
        }
        return max[rn - 1][cn - 1];
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
