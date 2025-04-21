import java.util.*;

class Solution {
    private void rotateOneLayer(int[][] matrix, int n, int x, int y) {
        int i, tmp;
        for (i = 0; i < n - 1; i++) {
            tmp = matrix[y][x + i];
            matrix[y][x + i] = matrix[y + n - 1 - i][x];
            matrix[y + n - 1 - i][x] = matrix[y + n - 1][x + n - 1 - i];
            matrix[y + n - 1][x + n - 1 - i] = matrix[y + i][x + n - 1];
            matrix[y + i][x + n - 1] = tmp;
        }
    }
    public void rotate(int[][] matrix) {
        int n = matrix.length, x = 0, y = 0;
        if (n == 1) {
            return;
        }
        while (n > 1) {
            rotateOneLayer(matrix, n, x, y);
            n = n - 2;
            x++;
            y++;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][]{
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16},
        };
        s.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
