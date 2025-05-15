import java.util.*;


class Solution {
    private void dfs(int n, int curK, int cur, List<Integer> comb, List<List<Integer>> res) {
        if (curK == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        if (n - cur >= curK) {
            dfs(n, curK, cur + 1, comb, res);
        }
        if (n - cur >= curK - 1) {
            comb.add(cur);
            dfs(n, curK - 1, cur + 1, comb, res);
            comb.remove(comb.size() - 1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        dfs(n, k, 1, comb, res);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 4, k = 2;
        List<List<Integer>> res = s.combine(n, k);
        System.out.println(res);
    }
}
