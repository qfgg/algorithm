import java.util.*;


class Solution {
    void dfs(int[] nums, int cur, List<Integer> sub, List<List<Integer>> res) {
        if (cur == nums.length) {
            res.add(new ArrayList<>(sub));
            return;
        }
        dfs(nums, cur + 1, sub, res);
        sub.add(nums[cur]);
        dfs(nums, cur + 1, sub, res);
        sub.remove(sub.size() - 1);
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        dfs(nums, 0, sub, res);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> res = s.subsets(nums);
        System.out.println(res);
    }
}
