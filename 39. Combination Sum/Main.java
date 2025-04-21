import java.util.*;

class Solution {
    private void dfs(int[] candidates, int target, int pos, int sum, List<Integer> l, List<List<Integer>> res) {
        if (sum == target) {
            List<Integer> copy = new ArrayList<>(l);
            res.add(copy);
            return;
        }
        if (pos >= candidates.length) {
            return;
        }
        int k = (target - sum) / candidates[pos], i, j;
        dfs(candidates, target, pos + 1, sum, l, res);
        for (i = 1; i <= k; i++) {
            for (j = 1; j <= i; j++) {
                l.add(candidates[pos]);
            }
            dfs(candidates, target, pos + 1, sum + i * candidates[pos], l, res);
            for (j = 1; j <= i; j++) {
                l.remove(l.size() - 1);
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        dfs(candidates, target, 0, 0, l, res);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] candidates = new int[]{2,3,5};
        List<List<Integer>> res = s.combinationSum(candidates, 8);
        System.out.println(res);
    }
}
