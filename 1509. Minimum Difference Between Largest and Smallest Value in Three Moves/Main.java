import java.util.*;


class Solution {
    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n < 5) {
            return 0;
        }
        Arrays.sort(nums);
        int diff = nums[n - 1] - nums[0];
        int removal = nums[n - 1] - nums[n - 4];
        removal = Math.max(removal, nums[n - 1] - nums[n - 3] + nums[1] - nums[0]);
        removal = Math.max(removal, nums[n - 1] - nums[n - 2] + nums[2] - nums[0]);
        removal = Math.max(removal, nums[3] - nums[0]);
        return diff - removal;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,5,0,10,14};
        int res = s.minDifference(nums);
        System.out.println(res);
    }
}
