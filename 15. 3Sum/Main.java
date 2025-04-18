import java.util.*;


class Solution {
    private void twoSum(int[] nums, int pos, HashSet<List<Integer>> res) {
        int target = -nums[pos];
        HashSet<Integer> set = new HashSet<>();
        for (int i = pos + 1; i < nums.length; i++) {
            if (set.contains(target - nums[i])) {
                List<Integer> l = new ArrayList<>(Arrays.asList(nums[pos], target - nums[i], nums[i]));
                Collections.sort(l);
                res.add(l);
            }
            if (!set.contains((nums[i]))) {
                set.add(nums[i]);
            }
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                twoSum(nums, i, set);
            }
        }
        return new ArrayList<>(set);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{0,0,0,0};
        List<List<Integer>> res = s.threeSum(nums);
        System.out.println(res);
    }
}
