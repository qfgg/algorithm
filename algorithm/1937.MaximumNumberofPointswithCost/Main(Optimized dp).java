import java.util.*;

public class Main {
    public static long maxPoints(int[][] points) {
        int yn = points.length, xn = points[0].length, i, j;
        long[] dp = new long[xn];
        long[] suffixMax = new long[xn];
        long[] prefixMax = new long[xn];
        long max = 0;
        for (i = 0; i < xn; i++) {
            dp[i] = points[0][i];
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        for (i = 1; i < yn; i++) {
            suffixMax[xn - 1] = dp[xn - 1] - 1;
            for (j = xn - 2; j > 0; j--) {
                suffixMax[j] = Math.max(suffixMax[j + 1] - 1, dp[j] - 1);
            }
            prefixMax[0] = dp[0];
            for (j = 1; j < xn; j++) {
                prefixMax[j] = Math.max(prefixMax[j - 1] - 1, dp[j]);
            }
            for (j = 0; j < xn - 1; j++) {
                dp[j] = Math.max(prefixMax[j], suffixMax[j + 1]) + points[i][j];
                if (dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[xn - 1] = prefixMax[xn - 1] + points[i][j];
            if (dp[xn -1] > max) {
                max = dp[xn - 1];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[][] points = new int[][]{{1, 2, 3}, {1, 5, 1}, {3, 1, 1}};
        long ans = maxPoints(points);
        System.out.println(ans);
    }
}
