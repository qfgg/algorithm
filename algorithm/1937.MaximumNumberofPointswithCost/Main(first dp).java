import java.util.*;

public class Main {
    public static long maxPoints(int[][] points) {
        int ylen = points.length, xlen = points[0].length, i, j, k;
        long[][] dp = new long[ylen][xlen];
        long lastRowMax = 0, tmp;
        for (i = 0; i < xlen; i++) {
            dp[0][i] = points[0][i];
            if (dp[0][i] > lastRowMax) {
                lastRowMax = dp[0][i];
            }
        }
        if (ylen == 1) {
            return lastRowMax;
        }
        lastRowMax = 0;
        for (i = 1; i < ylen; i++) {
            for (j = 0; j < xlen; j++) {
                tmp = 0;
                for (k = 0; k < xlen; k++) {
                    tmp = Math.max(tmp, dp[i - 1][k] + points[i][j] - Math.abs(k - j));
                }
                dp[i][j] = tmp;
                if (i == ylen - 1 && dp[i][j] > lastRowMax) {
                    lastRowMax = dp[i][j];
                }
            }
        }
        return lastRowMax;
    }
    public static void main(String[] args) {
        int[][] points = new int[][]{{1, 2, 3}, {1, 5, 1}, {3, 1, 1}};
        long ans = maxPoints(points);
        System.out.println(ans);
    }
}
