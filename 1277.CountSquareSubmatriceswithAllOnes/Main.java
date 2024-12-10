import java.util.*;

public class Main {
    public static int countSquares(int[][] matrix) {
        int rn = matrix.length, cn = matrix[0].length, i, j;
        int[][] f = new int[rn][cn];
        int cnt = 0;
        for (j = 0; j < cn; j++) {
            if (matrix[0][j] == 1) {
                f[0][j] = matrix[0][j];
                cnt++;
            }
        }
        for (i = 1; i < rn; i++) {
            if (matrix[i][0] == 1) {
                f[i][0] = matrix[i][0];
                cnt++;
            }
        }
        for (i = 1; i < rn; i++) {
            for (j = 1; j < cn; j++) {
                if (matrix[i][j] == 1) {
                    f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;
                    cnt += f[i][j];
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0,0,0},
                {0,1,0},
                {0,1,0},
                {1,1,1},
                {1,1,0}
        };
        int res = countSquares(matrix);
        System.out.println(res);
    }
}
