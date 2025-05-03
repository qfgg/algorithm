import java.util.*;


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rn = matrix.length, cn = matrix[0].length, l = 0, r = rn * cn, m, y, x;
        while (l < r) {
            m = (l + r) / 2;
            y = m / cn;
            x = m % cn;
            if (target == matrix[y][x]) {
                return true;
            }
            if (target < matrix[y][x]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int[][] matrix = new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60},
        };
        boolean res = slt.searchMatrix(matrix, 60);
        System.out.println(res);
    }
}
