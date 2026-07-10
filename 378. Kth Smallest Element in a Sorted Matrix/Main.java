import java.util.*;


class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (k == 1) {
            return matrix[0][0];
        }
        int n = matrix.length, count = 0, ny, nx, ret = matrix[0][0];
        int[] cur = new int[]{0, 0};
        PriorityQueue<int[]> mins = new PriorityQueue<>((a, b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);
        mins.add(cur);
        while (count < k) {
            cur = mins.poll();
            ret = matrix[cur[0]][cur[1]];
            matrix[cur[0]][cur[1]] = Integer.MAX_VALUE;
            count++;
            if (count == k) {
                break;
            }
            ny = cur[0];
            nx = cur[1] + 1;
            if (nx < n && (ny - 1 < 0 || matrix[ny - 1][nx] == Integer.MAX_VALUE)) {
                mins.add(new int[]{ny, nx});
            }
            ny = cur[0] + 1;
            nx = cur[1];
            if (ny < n && (nx - 1 < 0 || matrix[ny][nx - 1] == Integer.MAX_VALUE)) {
                mins.add(new int[]{ny, nx});
            }
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][]{
                {1,5,9},
                {10,11,13},
                {12,13,15},
        };
        int k = 8;
        int res = s.kthSmallest(matrix, k);
        System.out.println(res);
    }
}
