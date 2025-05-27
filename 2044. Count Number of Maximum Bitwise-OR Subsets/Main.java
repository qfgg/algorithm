import java.util.*;


class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length, max = 1 << n, i = 1, maxor = 0, count = 0, bit, j;
        for (int it : nums) {
            maxor = maxor | it;
        }
        int[] dp = new int[max];
        while (i < max) {
            bit = 1;
            j = 0;
            while ((i & bit) != bit) {
                bit = bit << 1;
                j++;
            }
            dp[i] = dp[i ^ bit] | nums[j];
            if (dp[i] == maxor) {
                count++;
            }
            i++;
        }
        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{3,2,1,5};
        long res = s.countMaxOrSubsets(nums);
        System.out.println(res);
    }
}
