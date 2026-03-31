import java.util.*;


class Solution {
    int getCompatibility(int[] ans1, int[] ans2) {
        int i, n = ans1.length, cp = 0;
        for (i = 0; i < n; i++) {
            if (ans1[i] == ans2[i]) {
                cp++;
            }
        }
        return cp;
    }
    int[][] computeCp(int[][] students, int[][] mentors) {
        int m = students.length, i, j;
        int[][] cp = new int[m][m];
        for (i = 0; i < m; i++) {
            for (j = 0; j < m; j++) {
                cp[i][j] = getCompatibility(students[i], mentors[j]);
            }
        }
        return cp;
    }
    void findMax(int sidx, int bits, int[][] cp, int[][] dp) {
        if (sidx == 0) {
            return;
        }
        int m = dp.length, i, bit, preBit, max = 0;
        for (i = 0; i < m; i++) {
            bit = 1 << i;
            if ((bits & bit) > 0) {
                preBit = bits ^ bit;
                findMax(sidx - 1, preBit, cp, dp);
                max = Math.max(max, dp[sidx - 1][preBit] + cp[sidx][i]);
            }
        }
        dp[sidx][bits] = max;
    }
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length, i, j, k = 1 << m;
        int[][] cp = computeCp(students, mentors);
        int[][] dp =  new int[m][k];
        for (i = 0; i < m; i++) {
            dp[0][1 << i] = cp[0][i];
        }
        findMax(m - 1, k - 1, cp, dp);
        return dp[m - 1][k - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] students = new int[][]{
                {1,1,0},
                {1,0,1},
                {0,0,1},
        };
        int[][] mentors = new int[][]{
                {1,0,0},
                {0,0,1},
                {1,1,0},
        };
        int res = s.maxCompatibilitySum(students, mentors);
        System.out.println(res);
    }
}
