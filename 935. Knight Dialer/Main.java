import java.util.*;


class Solution {
    public int knightDialer(int n) {
        if (n == 1) {
            return 10;
        }
        int i, j, mod = 1000000007, total = 0;
        int[] pre = new int[10], cur = new int[10], tmp;
        Arrays.fill(pre, 1);
        for (i = 2; i <= n; i++) {
            for (j = 0; j < 10; j++) {
                if (j == 0) {
                    cur[4] = (cur[4] + pre[j]) % mod;
                    cur[6] = (cur[6] + pre[j]) % mod;
                } else if (j == 1) {
                    cur[8] = (cur[8] + pre[j]) % mod;
                    cur[6] = (cur[6] + pre[j]) % mod;
                } else if (j == 2) {
                    cur[7] = (cur[7] + pre[j]) % mod;
                    cur[9] = (cur[9] + pre[j]) % mod;
                } else if (j == 3) {
                    cur[4] = (cur[4] + pre[j]) % mod;
                    cur[8] = (cur[8] + pre[j]) % mod;
                } else if (j == 4) {
                    cur[3] = (cur[3] + pre[j]) % mod;
                    cur[9] = (cur[9] + pre[j]) % mod;
                    cur[0] = (cur[0] + pre[j]) % mod;
                } else if (j == 6) {
                    cur[1] = (cur[1] + pre[j]) % mod;
                    cur[7] = (cur[7] + pre[j]) % mod;
                    cur[0] = (cur[0] + pre[j]) % mod;
                } else if (j == 7) {
                    cur[2] = (cur[2] + pre[j]) % mod;
                    cur[6] = (cur[6] + pre[j]) % mod;
                } else if (j == 8) {
                    cur[1] = (cur[1] + pre[j]) % mod;
                    cur[3] = (cur[3] + pre[j]) % mod;
                } else if (j == 9) {
                    cur[4] = (cur[4] + pre[j]) % mod;
                    cur[2] = (cur[2] + pre[j]) % mod;
                }
            }
            if (i < n) {
                tmp = pre;
                pre = cur;
                cur = tmp;
                Arrays.fill(cur, 0);
            }
        }
        for (j = 0; j < 10; j++) {
            total = (total + cur[j]) % mod;
        }
        return total;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 2;
        int res = s.knightDialer(n);
        System.out.println(res);
    }
}
