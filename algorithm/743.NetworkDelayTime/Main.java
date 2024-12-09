import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int max = 0;
        Set<Integer> done = new HashSet<>();
        Map<Integer, List<int[]>> e = new HashMap<>();
        for (int[] time : times) {
            e.putIfAbsent(time[0], new ArrayList<>());
            e.get(time[0]).add(new int[]{time[1], time[2]});
        }
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[k] = 0;
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{k, 0});
        int[] cur;
        List<int[]> vs;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            while (cur != null && cur[1] != d[cur[0]]) {
                cur = pq.poll();
            }
            if (cur == null) {
                break;
            }
            done.add(cur[0]);
            if (cur[1] > max) {
                max = cur[1];
            }
            if (e.containsKey(cur[0])) {
                vs = e.get(cur[0]);
                for (int[] v : vs) {
                    if (!done.contains(v[0]) && cur[1] + v[1] < d[v[0]]) {
                        d[v[0]] = cur[1] + v[1];
                        pq.add(new int[]{v[0], d[v[0]]});
                    }
                }
            }
        }
        return done.size() == n ? max : -1;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] times = new int[][]{{1,2,1},{2,3,2},{1,3,4}};
        int n = 3, k = 1;
        int res = s.networkDelayTime(times, n, k);
        System.out.println(res);
    }
}
