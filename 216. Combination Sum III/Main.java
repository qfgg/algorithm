import java.util.*;


class Solution {
    private void dfs(int k, int n, int cur, List<Integer> comb, List<List<Integer>> res) {
        if (n == 0 && k == 0) {
            res.add(new ArrayList<>(comb));
        }
        if (n == 0 || k == 0 || cur > 9) {
            return;
        }
        dfs(k, n, cur + 1, comb, res);
        if (cur <= n && k > 0) {
            comb.add(cur);
            dfs(k - 1, n - cur, cur + 1, comb, res);
            comb.remove(comb.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        dfs(k, n, 1, comb, res);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int k = 3, n = 24;
        List<List<Integer>> res = s.combinationSum3(k, n);
        System.out.println(res);
    }
}
