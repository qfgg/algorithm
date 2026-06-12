import java.util.*;


class NumMatrix {
    int[][] presum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, i, j;
        presum = new int[m][n];
        for (i = 0; i < m; i++) {
            presum[i][0] = matrix[i][0];
        }
        for (i = 0; i < m; i++) {
            for (j = 1; j < n; j++) {
                presum[i][j] = presum[i][j - 1] + matrix[i][j];
            }
        }
        for (i = 1; i < m; i++) {
            for (j = 0; j < n; j++) {
                presum[i][j] = presum[i - 1][j] + presum[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return presum[row2][col2] - (
                col1 == 0 ? 0 : presum[row2][col1 - 1]
        ) - (
                row1 == 0 ? 0 : presum[row1 - 1][col2]
        ) + (
                row1 == 0 || col1 == 0 ? 0 : presum[row1 - 1][col1 - 1]
        );
    }
}
public class Main {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix NM = new NumMatrix(matrix);
        System.out.println(NM.sumRegion(2, 1, 4, 3));
        System.out.println(NM.sumRegion(1, 1, 2, 2));
        System.out.println(NM.sumRegion(1, 2, 2, 4));
    }
}
