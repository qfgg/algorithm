import java.util.*;


class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        int n = time.length, i, max = time[0];
        long l, r, m, count, minTime;
        for (i = 1; i < n; i++) {
            if (time[i] > max) {
                max = time[i];
            }
        }
        r = (long)(totalTrips / n + (totalTrips % n == 0 ? 0 : 1)) * max;
        l = 1;
        minTime = r;
        while (l < r) {
            m = (l + r) / 2;
            count = 0;
            for (i = 0; i < n; i++) {
                count += m / time[i];
            }
            if (count >= totalTrips) {
                minTime = Math.min(minTime, m);
                r = m;
            } else {
                l = m + 1;
            }
        }
        return minTime;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] time = new int[]{5,10,10};
        int totalTrips = 9;
        long res = s.minimumTime(time, totalTrips);
        System.out.println(res);
    }
}
