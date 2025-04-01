import java.util.*;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length, i, max = 1, j;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (i = 1; i < len; i++) {
            if (nums[i] > dp[max - 1]) {
                max++;
                dp[max - 1] = nums[i];
            } else if (nums[i] <= dp[0]) {
                dp[0] = nums[i];
            } else {
                j = max - 1;
                while (j >= 1) {
                    if (nums[i] <= dp[j] && nums[i] > dp[j - 1]) {
                        dp[j] = nums[i];
                        break;
                    }
                    j--;
                }
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 0,1,5,2,0,5,0,3,5,4,0 };
        int res = s.lengthOfLIS(nums);
        System.out.println(res);
    }
}
