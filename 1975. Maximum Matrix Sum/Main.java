import java.util.*;


class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long res = 0;
        int maxNegtive = Integer.MIN_VALUE, minPositive = Integer.MAX_VALUE;
        boolean oddNegtive = false, hasZero = false;
        for (int[] row : matrix) {
            for (int it : row) {
                if (it > 0) {
                    minPositive = Math.min(minPositive, it);
                    res += it;
                } else if (it < 0) {
                    maxNegtive = Math.max(maxNegtive, it);
                    res -= it;
                    oddNegtive = !oddNegtive;
                } else {
                    hasZero = true;
                }
            }
        }
        if (hasZero || !oddNegtive) {
            return res;
        }
        if (maxNegtive + minPositive < 0) {
            res -= (long)minPositive * 2;
        } else {
            res += (long)maxNegtive * 2;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][]{{2,9,3},{5,4,-4},{1,7,1}};
        long res = s.maxMatrixSum(matrix);
        System.out.println(res);
    }
}
