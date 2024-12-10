import java.util.*;

public class Main {
    public static int maximalRectangle(char[][] matrix) {
        int rn = matrix.length, cn = matrix[0].length, i, j, k, max = 0, area, minw;
        int[][] f = new int[rn][cn];
        for (i = 0; i < rn; i++) {
            for (j = 0; j < cn; j++) {
                if (matrix[i][j] == '1') {
                    f[i][j] = j > 0 ? f[i][j - 1] + 1 : 1;
                }
                minw = cn;
                for (k = 0; k <= i; k++) {
                    if (f[i - k][j] == 0) {
                        break;
                    }
                    if (f[i - k][j] < minw) {
                        minw = f[i - k][j];
                    }
                    area = minw * (k + 1);
                    if (area > max) {
                        max = area;
                    }
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        int res = maximalRectangle(matrix);
        System.out.println(res);
    }
}
