import java.util.*;


public class Main {
    public static int[] getSumAbsoluteDifferences(int[] nums) {
        int len = nums.length, i;
        int[] presum = new int[len];
        int[] ans = new int[len];
        presum[0] = nums[0];
        for (i = 1; i < len; i++) {
            presum[i] = presum[i - 1] + nums[i];
        }
        ans[0] = presum[len - 1] - nums[0] * len;
        ans[len - 1] = nums[len - 1] * len - presum[len - 1];
        for (i = 1; i < len - 1; i++) {
            ans[i] = (2 * i - len + 2) * nums[i] - 2 * presum[i] + presum[len - 1];
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,4,6,8,10};
        int[] ans = getSumAbsoluteDifferences(nums);
        System.out.println(Arrays.toString(ans));
    }
}
