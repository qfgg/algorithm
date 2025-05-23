import java.util.*;


class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length, i, j, sign = n % 2 == 1 ? 1 : -1;
        int[][] dp = new int[n][n]; // dp[x][y], x, y: start, end indices
        for (i = 0; i < n; i++) {
            dp[i][i] = nums[i] * sign;
        }
        sign = -sign;
        for (i = 1; i < n; i++) {
            for (j = 0; j < n - i; j++) {
                if (sign == 1) {
                    dp[j][j + i] = Math.max(dp[j][j + i - 1] + nums[j + i], dp[j + 1][j + i] + nums[j]);
                } else {
                    dp[j][j + i] = Math.min(dp[j][j + i - 1] - nums[j + i], dp[j + 1][j + i] - nums[j]);
                }
            }
            sign = -sign;
        }
        return dp[0][n - 1] >= 0;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,5,233,7};
        boolean res = s.predictTheWinner(nums);
        System.out.println(res);
    }
}
