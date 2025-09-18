import java.util.*;


class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n == 1) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int cur = 0, next = 1, cnt = 0;
        while (next < n) {
            if (intervals[next][0] < intervals[cur][1]) {
                if (intervals[next][1] < intervals[cur][1]) {
                    cur = next;
                }
                next++;
                cnt++;
            } else {
                cur = next;
                next++;
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals = new int[][]{
                {1,2},{3,4},{2,3},{1,3},
        };
        int res = s.eraseOverlapIntervals(intervals);
        System.out.println(res);
    }
}
