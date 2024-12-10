import java.util.*;

public class Main {
    public static int longestLine(int[][] mat) {
        int rn = mat.length, cn = mat[0].length, max = 0, i, j, k;
        // 0: left, 1: anti diag, 2: up, 3: diag
        int[][][] dp = new int[rn][cn][4];
        for (i = 0; i < rn; i++) {
            for (j = 0; j < cn; j++) {
                if (mat[i][j] == 1) {
                    dp[i][j][0] = j == 0 ? 1 : dp[i][j - 1][0] + 1;
                    dp[i][j][1] = i == 0 || j == 0 ? 1 : dp[i - 1][j - 1][1] + 1;
                    dp[i][j][2] = i == 0 ? 1 : dp[i - 1][j][2] + 1;
                    dp[i][j][3] = i == 0 || j == cn - 1 ? 1 : dp[i - 1][j + 1][3] + 1;
                    for (k = 0; k < 4; k++) {
                        max = Math.max(max, dp[i][j][k]);
                    }
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Random r = new Random();
        int[][] mat = new int[5][6];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                mat[i][j] = r.nextInt(2);
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
        int res = longestLine(mat);
        System.out.println(res);
    }
}
