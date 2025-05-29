import java.util.*;


class Solution {
    void dfs(List<Integer> n, int cur, int len, HashMap<Integer, Integer> count, List<Integer> p, List<List<Integer>> res) {
        if (cur == len) {
            res.add(new ArrayList<>(p));
            return;
        }
        int curCount;
        for (Integer num : n) {
            curCount = count.get(num);
            if (curCount > 0) {
                p.add(num);
                count.put(num, curCount - 1);
                dfs(n, cur + 1, len, count, p, res);
                p.remove(p.size() - 1);
                count.put(num, curCount);
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        int i, len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> n = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        for (i = 0; i < len; i++) {
            if (count.containsKey(nums[i])) {
                count.put(nums[i], count.get(nums[i]) + 1);
            } else {
                count.put(nums[i], 1);
                n.add(nums[i]);
            }
        }
        dfs(n, 0, len, count, p, res);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,2,1,1};
        List<List<Integer>> res = s.permuteUnique(nums);
        System.out.println(res);
    }
}
