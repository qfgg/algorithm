import java.util.*;


class Solution {
    void dfs(List<Integer> n, HashMap<Integer, Integer> count, int target, int cur, List<Integer> comb, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        if (cur == n.size()) {
            return;
        }
        dfs(n, count, target, cur + 1, comb, res);
        int i, j, curN = n.get(cur), k = count.get(curN);
        for (i = 1; i <= k; i++) {
            if (target >= curN * i) {
                for (j = 0; j < i; j++) {
                    comb.add(curN);
                }
                dfs(n, count, target - curN * i, cur + 1, comb, res);
                for (j = 0; j < i; j++) {
                    comb.remove(comb.size() - 1);
                }
            }
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> n = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int c : candidates) {
            if (count.containsKey(c)) {
                count.put(c, count.get(c) + 1);
            } else {
                n.add(c);
                count.put(c, 1);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        dfs(n, count, target, 0, comb, res);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        List<List<Integer>> res = s.combinationSum2(candidates, 8);
        System.out.println(res);
    }
}
