import java.util.*;


class Solution {
    private boolean check(int n, int np, int qn, int[] quantities) {
        int sum = 0, i;
        for (i = 0; i < qn; i++) {
            sum += quantities[i] / np + (quantities[i] % np == 0 ? 0 : 1);
        }
        return sum <= n;
    }
    public int minimizedMaximum(int n, int[] quantities) {
        int i, qn = quantities.length, minMax = 0, low = 1, high = Integer.MAX_VALUE, mid;
        for (i = 0; i < qn; i++) {
            minMax = Math.max(minMax, quantities[i]);
            high = minMax;
        }
        while (low < high) {
            mid = (low + high) / 2;
            if (check(n, mid, qn, quantities)) {
                minMax = Math.min(mid, minMax);
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return minMax;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] quantities = new int[]{11,6};
        int n = 6;
        int res = s.minimizedMaximum(n, quantities);
        System.out.println(res);
    }
}
