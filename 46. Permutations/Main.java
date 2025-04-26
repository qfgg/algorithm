import java.util.*;


class Solution {
    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
    private void dfs(int[] nums, int start, int end, List<List<Integer>> res) {
        if (start == end) {
            List<Integer> copy = new ArrayList<>();
            for (int num : nums) {
                copy.add(num);
            }
            res.add(copy);
            return;
        }
        int i;
        dfs(nums, start + 1, end, res);
        for (i = start + 1; i <= end ; i++) {
            swap(nums, start, i);
            dfs(nums, start + 1, end, res);
            swap(nums, start, i);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, nums.length - 1, res);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> res = s.permute(nums);
        System.out.println(res);
    }
}
