import java.util.*;

class Solution {
    public int findLower(int[][] intervals, int target) {
        int l = 0, r = intervals.length, m;
        while (l < r) {
            m = l + ((r - l) >> 1);
            if (intervals[m][0] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l - 1;
    }
    public int findHigher(int[][] intervals, int target) {
        int l = 0, r = intervals.length, m;
        while (l < r) {
            m = l + ((r - l) >> 1);
            if (intervals[m][1] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> r = new ArrayList<>();
        int n = intervals.length, i;
        int i1 = findLower(intervals, newInterval[0]);
        int i2 = findHigher(intervals, newInterval[1]);
        for (i = 0; i < i1; i++) {
            r.add(intervals[i]);
        }
        if (i1 == -1 && i2 == n) {
            r.add(newInterval);
        } else if (i1 == -1) {
            if (intervals[i2][0] <= newInterval[1]) {
                r.add(new int[]{newInterval[0], intervals[i2][1]});
            } else {
                r.add(newInterval);
                r.add(intervals[i2]);
            }
        } else if (i2 == n) {
            if (intervals[i1][1] >= newInterval[0]) {
                r.add(new int[]{intervals[i1][0], newInterval[1]});
            } else {
                r.add(intervals[i1]);
                r.add(newInterval);
            }
        } else {
            if (intervals[i1][1] >= newInterval[0] && intervals[i2][0] <= newInterval[1]) {
                r.add(new int[]{intervals[i1][0], intervals[i2][1]});
            } else if (intervals[i1][1] >= newInterval[0]) {
                r.add(new int[]{intervals[i1][0], newInterval[1]});
                r.add(intervals[i2]);
            } else if (intervals[i2][0] <= newInterval[1]) {
                r.add(intervals[i1]);
                r.add(new int[]{newInterval[0], intervals[i2][1]});
            } else {
                r.add(intervals[i1]);
                r.add(newInterval);
                r.add(intervals[i2]);
            }
        }
        for (i = i2 + 1; i < n; i++) {
            r.add(intervals[i]);
        }
        n = r.size();
        int[][] res = new int[n][2];
        for (i = 0; i < n; i++) {
            res[i] = r.get(i);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = new int[]{0,17};
        int[][] res = s.insert(intervals, newInterval);
        System.out.println(Arrays.deepToString(res));
    }
}
