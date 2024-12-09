import java.util.*;

// cannot pass case because 10 4 3 2 1, 01 knapsack cannot control every choice
// (prefer 4,1 to 3,2, but 3,2 first done)
// int[] tasks = new int[]{1, 2, 2, 3, 3, 3, 4, 4, 9, 9, 10, 10};
// int sessionTime = 15;
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

        int num = tasks.length;
        int leftSpace = sessionTime, maxTask = 0;
        int j;
        for (j = num - 1; j >= 0; j--) {
            if ((taken & (1 << j)) == 0) {
                taken = taken | (1 << j);
                maxTask = tasks[j];
                leftSpace = sessionTime - tasks[j];
                j--;
                break;
            }
        }
        int[] dp = new int[leftSpace + 1];
        int[] status = new int[leftSpace + 1];
        for (; j >= 0; j--) {
            if ((taken & (1 << j)) == 0) {
                for (int i = leftSpace; i >= tasks[j]; i--) {
                    if (dp[i - tasks[j]] + tasks[j] > dp[i]) {
                        dp[i] = dp[i - tasks[j]] + tasks[j];
                        status[i] = status[i - tasks[j]] | (1 << j);
                    }
                }
            }
        }
        fillOne(tasks, sessionTime, rest - dp[leftSpace] - maxTask, taken | status[leftSpace], n + 1, min);
        if (leftSpace > 0) {
            fillOne(tasks, sessionTime, rest - dp[leftSpace - 1] - maxTask, taken | status[leftSpace - 1], n + 1, min);
        }
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
