import java.util.*;


class Solution {
    private boolean check(int[] weights, int capacity, int k) {
        int i = 0, c = capacity, n = weights.length;
        while (k > 0 && i < n) {
            if (c >= weights[i]) {
                c -= weights[i];
                i++;
            } else {
                k--;
                c = capacity;
            }
        }
        return i == n;
    }
    public int shipWithinDays(int[] weights, int days) {
        int i, n = weights.length, l = 1, r = weights[0], m, min = Integer.MAX_VALUE;
        for (i = 1; i < n; i++) {
            r += weights[i];
        }
        r++;
        while (l < r) {
            m = (l + r) / 2;
            if (check(weights, m, days)) {
                r = m;
                min = Math.min(min, m);
            } else {
                l = m + 1;
            }
        }
        return min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] weights = new int[]{1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        int res = s.shipWithinDays(weights, days);
        System.out.println(res);
    }
}
