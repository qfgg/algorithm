import java.util.*;


class Solution {
    public int numTilings(int n) {
        int mod = 1000000007, i;
        long[] f = new long[n + 1], f1 = new long[n + 1], f2 = new long[n + 1]; // f1: 1 more up square, f2: 1 more lower square
        f[0] = 1;
        f[1] = 1;
        for (i = 2; i <= n; i++) {
            f1[i] = (f[i - 2] + f2[i - 1]) % mod;
            f2[i] = (f[i - 2] + f1[i - 1]) % mod;
            f[i] = (f[i - 1] + f[i - 2] + f1[i - 1] + f2[i - 1]) % mod;
        }
        return (int)f[n];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 30;
        int res = s.numTilings(n);
        System.out.println(res);
    }
}
