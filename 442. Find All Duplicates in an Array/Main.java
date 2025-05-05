import java.util.*;


class Solution {
    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int i, n = nums.length;
        for (i = 0; i < n; i++) {
            while (nums[i] != i + 1) {
                if (nums[i] != 0 && nums[i] == nums[nums[i] - 1]) {
                    res.add(nums[i]);
                    nums[i] = 0;
                    break;
                }
                if (nums[i] != 0) {
                    swap(nums, i, nums[i] - 1);
                } else {
                    break;
                }
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        List<Integer> res = s.findDuplicates(nums);
        System.out.println(res);
    }
}
