import java.util.*;


class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0, n = nums.length, min = Integer.MAX_VALUE, sum = 0;
        while(l <= r && r < n) {
            sum += nums[r];
            while (sum >= target && l <= r) {
                min = Math.min(min, r - l + 1);
                sum -= nums[l];
                l++;
            }
            if (l > r) {
                return 1;
            }
            r++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int target = 7;
        int[] nums = new int[]{2,3,1,2,4,7};
        int res = s.minSubArrayLen(target, nums);
        System.out.println(res);
    }
}
