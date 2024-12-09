import java.util.*;


class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length, i, leftEnd;
        int[] f = new int[n];
        for (i = 0; i < n; i++) {
            f[i] = i > 0 ? f[i - 1] + costs[0] : costs[0];
            leftEnd = Math.max(i - 7, 0);
            while (days[i] - days[leftEnd] > 6) {
                leftEnd++;
            }
            f[i] = Math.min(f[i], leftEnd > 0 ? f[leftEnd - 1] + costs[1] : costs[1]);
            leftEnd = Math.max(i - 30, 0);
            while (days[i] - days[leftEnd] > 29) {
                leftEnd++;
            }
            f[i] = Math.min(f[i], leftEnd > 0 ? f[leftEnd - 1] + costs[2] : costs[2]);
        }
        return f[n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] days = new int[]{1,2,3,4,6,8,9,10,13,14,16,17,19,21,24,26,27,28,29};
        int[] costs = new int[]{3,14,50};
        int res = s.mincostTickets(days, costs);
        System.out.println(res);
    }
}
