import java.util.*;

class Solution {
    public int uniquePaths(int m, int n) {
        int[] f = new int[n];
        f[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[j] = f[j] + (j > 0 ? f[j - 1] : 0);
            }
        }
        return f[n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int m = 3, n = 7;
        int res = s.uniquePaths(m, n);
        System.out.println(res);
    }
}
