import java.util.*;


class Solution {
    private void setRow0(int[][] matrix, int cn, int r) {
        for (int i = 0; i < cn; i++) {
            matrix[r][i] = 0;
        }
    }
    private void setCol0(int[][] matrix, int rn, int c) {
        for (int i = 0; i < rn; i++) {
            matrix[i][c] = 0;
        }
    }
    public void setZeroes(int[][] matrix) {
        int i, j, rn = matrix.length, cn = matrix[0].length;
        boolean isRow0 = false, isCol0 = false;
        for (i = 0; i < cn; i++) {
            if (matrix[0][i] == 0) {
                isRow0 = true;
                break;
            }
        }
        for (i = 0; i < rn; i++) {
            if (matrix[i][0] == 0) {
                isCol0 = true;
                break;
            }
        }
        for (i = 1; i < rn; i++) {
            for (j = 1; j < cn; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (i = 1; i < cn; i++) {
            if (matrix[0][i] == 0) {
                setCol0(matrix, rn, i);
            }
        }
        for (i = 1; i < rn; i++) {
            if (matrix[i][0] == 0) {
                setRow0(matrix, cn, i);
            }
        }
        if (isRow0) {
            setRow0(matrix, cn, 0);
        }
        if (isCol0) {
            setCol0(matrix, rn, 0);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][]{
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        s.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
