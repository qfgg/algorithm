import java.util.*;


public class Main {
    private static void dfs(int[] tasks, int sessionTime, int pos, List<Integer> sessions, int[] ans) {
        int n = sessions.size();
        if (n >= ans[0]) {
            return;
        }
        if (pos < 0) {
            ans[0] = n;
            return;
        }
        int savedSession, newSession;
        for (int i = 0; i < n; i++) {
            savedSession = sessions.get(i);
            newSession = savedSession + tasks[pos];
            if (newSession <= sessionTime) {
                sessions.set(i, newSession);
                dfs(tasks, sessionTime, pos - 1, sessions, ans);
                sessions.set(i, savedSession);
            }
        }
        sessions.add(tasks[pos]);
        dfs(tasks, sessionTime,pos - 1, sessions, ans);
        sessions.remove(sessions.size() - 1);
    }
    public static int minSessions(int[] tasks, int sessionTime) {
        Arrays.sort(tasks);
        List<Integer> sessions = new ArrayList<>();
        int[] ans = new int[]{tasks.length};
        dfs(tasks, sessionTime, tasks.length - 1, sessions, ans);
        return ans[0];
    }
    public static void main(String[] args) {
        int[] tasks = new int[]{1, 2, 2, 3, 3, 3, 4, 4, 9, 9, 10, 10};
        int sessionTime = 15;
        int ans = minSessions(tasks, sessionTime);
        System.out.println(ans);
    }
}
