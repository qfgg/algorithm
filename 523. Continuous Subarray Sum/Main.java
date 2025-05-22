import java.util.*;


class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length == 1) {
            return false;
        }
        HashMap<Integer, Integer> remainIdx = new HashMap<>();
        int n = nums.length, i, remain;
        int[] presum = new int[n];
        presum[0] = nums[0];
        remainIdx.put(nums[0] % k, 0);
        for (i = 1; i < n; i++) {
            presum[i] += presum[i - 1] + nums[i];
            remain = presum[i] % k;
            if (remain == 0 || (remainIdx.containsKey(remain) && i - remainIdx.get(remain) > 1)) {
                return true;
            }
            if (!remainIdx.containsKey(remain)) {
                remainIdx.put(remain, i);
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{5,0,0,0};
        boolean res = s.checkSubarraySum(nums, 3);
        System.out.println(res);
    }
}
