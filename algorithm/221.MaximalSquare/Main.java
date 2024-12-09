import java.util.*;


public class Main {
    public static int maximalSquare(char[][] matrix) {
        int i, j, rl = matrix.length, cl = matrix[0].length, max = 0;
        int[][] dp = new int[rl][cl];
        for (j = 0; j < cl; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            if (dp[0][j] > max) {
                max = 1;
            }
        }
        for (i = 1; i < rl; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            if (dp[i][0] > max) {
                max = 1;
            }
        }
        for (i = 1; i < rl; i++) {
            for (j = 1; j < cl; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    if (dp[i - 1][j] < dp[i][j - 1]) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j -1]) + 1;
                    } else {
                        dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j -1]) + 1;
                    }
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max * max;
    }
    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int ans = maximalSquare(matrix);
        System.out.println(ans);
    }
}
