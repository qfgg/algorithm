import java.util.*;


class Solution {
    public int maxEvents(int[][] events) {
        // order by ends, so that earlier event is considered first
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int cnt = 0, n = events.length, i, min = 100001, max = 0;
        for (i = 0; i < n; i++) {
            min = Math.min(min, events[i][0]);
            max = Math.max(max, events[i][1]);
        }
        TreeSet<Integer> time = new TreeSet<>();
        for (i = min; i <= max; i++) {
            time.add(i);
        }
        Integer earlist;
        for (int[] event : events) {
            earlist = time.ceiling(event[0]);
            if (earlist != null && earlist <= event[1]) {
                cnt++;
                time.remove(earlist);
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] events = new int[][]{
                {1,3},
                {1,3},
                {1,3},
                {3,4},
        };
        int res = s.maxEvents(events);
        System.out.println(res);
    }
}
