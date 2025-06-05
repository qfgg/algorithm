import java.util.*;


class Solution {
    int dfs(int n, boolean left) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return left ? 2 : 1;
        }
        if (left) {
            return dfs(n / 2, false) * 2;
        }
        if (n % 2 == 1) {
            return dfs(n / 2, true) * 2;
        }
        return dfs((n + 1) / 2, true) * 2 - 1;
    }
    public int lastRemaining(int n) {
        return dfs(n, true);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.lastRemaining(6);
        System.out.println(res);
    }
}
