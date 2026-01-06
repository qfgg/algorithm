import java.util.*;


class Solution {
    int getNext(int origin, int p, boolean inc) {
        int digit = origin / p % 10, diff;
        if (inc) {
            diff = digit == 9 ? -9 : 1;
        } else {
            diff = digit == 0 ? 9 : -1;
        }
        return origin + p * diff;
    }
    public int openLock(String[] deadends, String target) {
        int n = deadends.length, i, j, t = Integer.parseInt(target), cur, next;
        if (t == 0) {
            return 0;
        }
        int[] min = new int[10000], p = new int[]{1, 10, 100, 1000};
        boolean[] isInc = new boolean[]{true, false};
        boolean[] visited = new boolean[10000];
        Arrays.fill(min, Integer.MAX_VALUE);
        for (i = 0; i < n; i++) {
            cur = Integer.parseInt(deadends[i]);
            if (cur == 0) {
                return -1;
            }
            min[cur] = -1;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;
        min[0] = 0;
        while (!q.isEmpty()) {
            cur = q.poll();
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 2; j++) {
                    next = getNext(cur, p[i], isInc[j]);
                    if (next == t) {
                        return min[cur] + 1;
                    }
                    if (!visited[next] && min[next] != -1) {
                        q.add(next);
                        min[next] = min[cur] + 1;
                        visited[next] = true;
                    }
                }
            }
        }
        return -1;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        int res = s.openLock(deadends, target);
        System.out.println(res);
    }
}
