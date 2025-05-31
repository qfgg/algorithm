import java.util.*;


class Solution {
    void dfs(int n, boolean[] col, boolean[] diag1, boolean[] diag2, int cur, int[] count) {
        if (cur == n) {
            count[0]++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!col[i] && !diag1[cur + i] && !diag2[cur - i + n - 1]) {
                col[i] = true;
                diag1[cur + i] = true;
                diag2[cur - i + n - 1] = true;
                dfs(n, col, diag1, diag2, cur + 1, count);
                col[i] = false;
                diag1[cur + i] = false;
                diag2[cur - i + n - 1] = false;
            }
        }
    }
    public int totalNQueens(int n) {
        if (n == 1) {
            return 1;
        }
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];
        int[] count = new int[1];
        dfs(n, col, diag1, diag2, 0, count);
        return count[0];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 4;
        int res = s.totalNQueens(n);
        System.out.println(res);
    }
}
