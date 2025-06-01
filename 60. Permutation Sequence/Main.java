import java.util.*;


class Solution {
    void dfs(int n, int k, int cur, boolean[] visited, int[] count, char[] p, String[] res) {
        if (cur == n) {
            count[0]++;
            if (count[0] == k) {
                res[0] = new String(p);
            }
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                p[cur] = (char)('0' + i);
                dfs(n, k, cur + 1,  visited, count, p, res);
                visited[i] = false;
                p[cur] = 0;
            }
        }
    }
    public String getPermutation(int n, int k) {
        char[] p = new char[n];
        boolean[] visited = new boolean[n + 1];
        int[] count = new int[1];
        String[] res = new String[1];
        dfs(n, k, 0, visited, count, p, res);
        return res[0];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 3, k = 3;
        String res = s.getPermutation(n, k);
        System.out.println(res);
    }
}
