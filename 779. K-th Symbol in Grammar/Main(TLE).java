import java.util.*;


class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        String dp0 = "0", dp1 = "1", ndp0, ndp1;
        for (int i = 2; i <= n; i++) {
            ndp0 = dp0 + dp1;
            ndp1 = dp1 + dp0;
            dp0 = ndp0;
            dp1 = ndp1;
        }
        return dp0.charAt(k - 1) - '0';
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.kthGrammar(30,434991989);
        System.out.println(res);
    }
}
