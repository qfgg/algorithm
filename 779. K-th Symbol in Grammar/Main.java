import java.util.*;


class Solution {
    int dfs(int n, int k, int who) {
        if (n == 1) {
            return who;
        }
        int cur = 1 << (n - 1), mid = cur / 2;
        if (k > mid) {
            return dfs(n - 1, k - mid, who == 0 ? 1 : 0);
        }
        return dfs(n - 1, k, who);
    }
    public int kthGrammar(int n, int k) {
        return dfs(n, k, 0);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.kthGrammar(2,2);
        System.out.println(res);
    }
}
