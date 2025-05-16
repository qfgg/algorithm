import java.util.*;


class Solution {
    private int dfs(int n, int[] memo) {
        if (memo[n] > 0) {
            return memo[n];
        }
        int count = 0, leftN, rightN;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                leftN = 1;
                rightN = dfs(n - 1, memo);
            } else if (i == n) {
                leftN = dfs(n - 1, memo);
                rightN = 1;
            } else {
                leftN = dfs(i - 1, memo);
                rightN = dfs(n - i, memo);
            }
            count += leftN * rightN;
        }
        memo[n] = count;
        return count;
    }
    public int numTrees(int n) {
        if (n == 1) {
            return 1;
        }
        int[] memo = new int[n + 1];
        memo[1] = 1;
        return dfs(n, memo);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.numTrees(6);
        System.out.println(res);
    }
}
