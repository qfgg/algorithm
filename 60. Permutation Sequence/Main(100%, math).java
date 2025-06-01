import java.util.*;


class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[n];
        int[] f = new int[n + 1];
        f[1] = 1;
        int i, j, m, q;
        for (i = 2; i <= n; i++) {
            f[i] = f[i - 1] * i;
        }
        i = n;
        while (i > 1 && k > 0) {
            m = (k - 1) / f[i - 1];
            k = k - m * f[i - 1];
            q = 1;
            j = 0;
            while (q <= n) {
                if (!used[q - 1]) {
                    j++;
                    if (j == m + 1) {
                        break;
                    }
                }
                q++;
            }
            sb.append(q);
            used[q - 1] = true;
            i--;
        }
        for (i = 0; i < n; i++) {
            if (!used[i]) {
                sb.append(i + 1);
            }
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 4, k = 9;
        String res = s.getPermutation(n, k);
        System.out.println(res);
    }
}
