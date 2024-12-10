import java.util.*;

public class Main {
    public static void fillOne(int[] tasks, int sessionTime, int rest, int taken, int n, int[] min) {
        if (n >= min[0]) {
            return;
        }
        if (rest == 0) {
            min[0] = n;
            return;
        }
        if (rest <= sessionTime) {
            min[0] = n + 1;
            return;
        }

        int[] dp = new int[sessionTime + 1];
        int[] status = new int[sessionTime + 1];
        for (int j = tasks.length - 1; j >= 0; j--) {
            if ((taken & (1 << j)) == 0) {
                for (int i = sessionTime; i >= tasks[j]; i--) {
                    if (dp[i - tasks[j]] + tasks[j] > dp[i]) {
                        dp[i] = dp[i - tasks[j]] + tasks[j];
                        status[i] = status[i - tasks[j]] | (1 << j);
                    }
                }
            }
        }
//        try last 2 distributions
        int savedTaken = taken;
        int savedRest = rest;
        rest = rest - dp[sessionTime];
        taken = taken | status[sessionTime];
        fillOne(tasks, sessionTime, rest, taken, n + 1, min);
        rest = savedRest;
        taken = savedTaken;
        rest = rest - dp[sessionTime - 1];
        taken = taken | status[sessionTime - 1];
        fillOne(tasks, sessionTime, rest, taken, n + 1, min);
    }
    public static int minSessions(int[] tasks, int sessionTime) {
        Arrays.sort(tasks);
        int rest = 0, taskNum = tasks.length, j;
        for (j = 0; j < taskNum; j++) {
            rest += tasks[j];
        }
        if (rest <= sessionTime) {
            return 1;
        }
        int[] min = new int[]{taskNum};
        fillOne(tasks, sessionTime, rest, 0, 0, min);
        return min[0];
    }

    public static void main(String[] args) {
        int[] tasks = new int[]{4,4,4,4,5,5,5,5,6,6,6,6,7,7};
        int sessionTime = 15;
        int ans = minSessions(tasks, sessionTime);
        System.out.println(ans);
    }
}
