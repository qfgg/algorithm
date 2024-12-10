import java.util.*;

public class Main {
    public static int leastInterval(char[] tasks, int n) {
        Set<Character> taskSet = new HashSet<>();
        int tn = tasks.length, itv = 0, offset;
        int[] cnt = new int[26];
        int[] cooldown = new int[26];
        int[] cur;
        PriorityQueue<int[]> q = new PriorityQueue<>(
                (a, b) -> a[2] == b[2] ? b[1] - a[1] : a[2] - b[2]
        );
        for (char t : tasks) {
            cnt[t - 'A']++;
            taskSet.add(t);
        }
        for (Character t : taskSet) {
            q.add(new int[]{t - 'A', cnt[t - 'A'], 0});
        }
        while (tn > 0) {
            cur = q.poll();
            while (!q.isEmpty() && (cur[1] != cnt[cur[0]] || cur[2] != cooldown[cur[0]])) {
                cur = q.poll();
            }
            offset = 1;
            if (cur[2] > 0) {
                offset += cur[2];
            }
            itv += offset;
            cnt[cur[0]]--;
            cooldown[cur[0]] = n;
            tn--;
            for (Character t : taskSet) {
                if (t - 'A' != cur[0]) {
                    if (cooldown[t - 'A'] > offset) {
                        cooldown[t - 'A'] -= offset;
                    } else {
                        cooldown[t - 'A'] = 0;
                    }
                    if (cnt[t - 'A'] > 0) {
                        q.add(new int[]{t - 'A', cnt[t - 'A'], cooldown[t - 'A']});
                    }
                }
            }
            q.add(new int[]{cur[0], cur[1] - 1, n});
        }
        return itv;
    }
    public static void main(String[] args) {
        char[] tasks = new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'};
        int res = leastInterval(tasks, 2);
        System.out.println(res);
    }
}
