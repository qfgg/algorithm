import java.util.*;

class Solution {
    public long minimumRemoval(int[] beans) {
        int n = beans.length;
        if (n == 1) {
            return 0;
        }
        Arrays.sort(beans);
        int i;
        long[] pre = new long[n];
        pre[0] = beans[0];
        for (i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + beans[i];
        }
        long res = Math.min(
                pre[n - 1] - pre[0] - (long)beans[0] * (n - 1),
                pre[n - 2]
        );
        for (i = 1; i < n - 1; i++) {
            res = Math.min(
                    pre[n - 1] - pre[i] - (long)beans[i] * (n - 1 - i) + pre[i - 1],
                    res
            );
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] beans = new int[]{4,1,6,5};
        long res = s.minimumRemoval(beans);
        System.out.println(res);
    }
}
