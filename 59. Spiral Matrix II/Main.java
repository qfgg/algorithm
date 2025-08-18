import java.util.*;


class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        int curDir = 0, r = 0, c = 0, nr, nc, val = 1, total = n * n;
        while (val <= total) {
            matrix[r][c] = val;
            nr = r + dir[curDir][1];
            nc = c + dir[curDir][0];
            if (nr < 0 || nr == n || nc < 0 || nc == n || matrix[nr][nc] > 0) {
                curDir = (curDir + 1) % 4;
                nr = r + dir[curDir][1];
                nc = c + dir[curDir][0];
            }
            r = nr;
            c = nc;
            val++;
        }
        return matrix;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 3;
        int[][] res = s.generateMatrix(n);
        System.out.println(Arrays.deepToString(res));
    }
}
