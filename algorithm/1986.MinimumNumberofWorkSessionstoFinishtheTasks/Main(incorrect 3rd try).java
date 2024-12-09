import java.util.*;

public class Main {
    public static void findOptimal(String[] dp, int end, int target, int max) {
        Set<String> comb = new HashSet<>();
        for (int i = 0; i <= end; i++) {
            if (dp[i] != null) {
                comb.add(dp[i]);
            }
        }
        int[] opt = null;
        int[] tmp;
        String[] arr;
        boolean hasSpace = false;
        for (String c : comb) {
            arr = c.split("#");
            int len = arr.length, j;
            int[] sessions = new int[len];
            for (j = 0; j < len; j ++) {
                sessions[j] = Integer.parseInt(arr[j]);
                if (!hasSpace && sessions[j] + target <= max) {
                    sessions[j] += target;
                    hasSpace = true;
                }
            }
            if (!hasSpace) {
                tmp = new int[len + 1];
                for (j = 0; j < len; j ++) {
                    tmp[j] = sessions[j];
                }
                tmp[len] = target;
                sessions = tmp;
            } else {
                hasSpace = false;
            }
            Arrays.sort(sessions);
            if (opt == null) {
                opt = sessions;
            } else {
                if (sessions.length < opt.length) {
                    opt = sessions;
                } else if (sessions.length == opt.length) {
                    for (j = sessions.length - 1; j >= 0; j--) {
                        if (sessions[j] == opt[j]) {
                            continue;
                        }
                        if (sessions[j] > opt[j]) {
                            opt = sessions;
                        }
                        break;
                    }
                }
            }
            int l = opt.length;
            arr = new String[l];
            for (j = l - 1; j >= 0; j--) {
                arr[l - 1 - j] = String.valueOf(opt[j]);
            }
            dp[end] = String.join("#", arr);
        }
    }
    public static int minSessions(int[] tasks, int sessionTime) {
        int num = tasks.length, i, j;
        String[] dp = new String[sessionTime + 1];
        Arrays.sort(tasks);
        for (j = sessionTime; j >= tasks[num - 1]; j--) {
            dp[j] = String.valueOf(tasks[num - 1]);
        }
        for (i = num - 2; i >= 0; i--) {
            for (j = sessionTime; j >= tasks[i]; j--) {
                findOptimal(dp, j, tasks[i], j);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[sessionTime].split("#").length;
    }

    public static void main(String[] args) {
        int[] tasks = new int[]{2,3,3,4,4,4,5,6,7,10};
        int sessionTime = 12;
        int ans = minSessions(tasks, sessionTime);
        System.out.println(ans);
    }
}
