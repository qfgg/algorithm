import java.util.*;


class Solution {
    public int maxSumMinProduct(int[] nums) {
        int i, n = nums.length, top;
        long max = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        long[] presum = new long[n];
        int[] r = new int[n];
        presum[0] = nums[0];
        for (i = 1; i < n; i++) {
            presum[i] = presum[i - 1] + nums[i];
        }
        for (i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                top = stack.pop();
                r[top] = i - 1;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            top = stack.pop();
            r[top] = n - 1;
        }
        for (i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                top = stack.pop();
                max = Math.max(max, nums[top] * (presum[r[top]] - presum[i]));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            top = stack.pop();
            max = Math.max(max, nums[top] * presum[r[top]]);
        }
        return (int)(max % 1000000007);
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] nums = new int[]{1,2,3,2};
      int res = s.maxSumMinProduct(nums);
      System.out.println(res);
  }
}

