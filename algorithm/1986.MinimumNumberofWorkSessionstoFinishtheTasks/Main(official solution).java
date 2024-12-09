import java.util.*;


public class Main {
    public static int minSessions(int[] tasks, int sessionTime) {
        int num = tasks.length, limit = 1 << num;
        int mask, bitmask, minSeg, minTime;
        int[][] dp = new int[limit][2];
        for (int i = 1; i < limit; i++) {
            minSeg = Integer.MAX_VALUE;
            minTime = Integer.MAX_VALUE;
            for (int j = 0; j < num; j++) {
                bitmask = 1 << j;
                if ((i & bitmask) > 0) {
                    mask = i ^ bitmask;
                    if (dp[mask][1] + tasks[j] <= sessionTime) {
                        dp[i][0] = dp[mask][0];
                        dp[i][1] = dp[mask][1] + tasks[j];
                    } else if (dp[mask][1] + tasks[j] == sessionTime) {
                        dp[i][0] = dp[mask][0] + 1;
                        dp[i][1] = 0;
                    } else {
                        dp[i][0] = dp[mask][0] + 1;
                        dp[i][1] = tasks[j];
                    }
                    if (dp[i][0] < minSeg ||
                            (dp[i][0] == minSeg && dp[i][1] < minTime)) {
                        minSeg = dp[i][0];
                        minTime = dp[i][1];
                    }
                }
            }
            dp[i][0] = minSeg;
            dp[i][1] = minTime;
        }
        return dp[limit - 1][1] > 0 ? dp[limit - 1][0] + 1 : dp[limit - 1][0];
    }

    public static void main(String[] args) {
        int[] tasks = new int[]{4,4,4,4,5,5,5,5,6,6,6,6,7,7};
        int sessionTime = 15;
        int ans = minSessions(tasks, sessionTime);
        System.out.println(ans);
    }
}
