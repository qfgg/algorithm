import java.util.*;


class Solution {
    int[] dfs(int n) {
        if (n == 1) {
            return new int[]{1};
        }
        int[] prev = dfs(n - 1);
        int[] ret = new int[n];
        ret[0] = prev[0];
        ret[n - 1] = prev[n - 2];
        for (int i = 1; i < n - 1; i++) {
            ret[i] = (prev[i] + prev[i - 1]) % 10;
        }
        return ret;
    }
    public int triangularSum(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int i, sum = 0;
        int[] cnt = dfs(nums.length);
        for (i = 0; i < n; i++) {
            sum = (sum + nums[i] * cnt[i]) % 10;
        }
        return sum;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] nums = new int[1000];
      int res = s.triangularSum(nums);
      System.out.println(res);
  }
}

