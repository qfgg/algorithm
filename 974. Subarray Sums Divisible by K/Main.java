import java.util.*;


class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length, i, count = 0, sum = 0, remain;
        int[] pr = new int[k];
        for (i = 0; i < n; i++) {
            sum += nums[i];
            remain = sum % k;
            if (remain < 0) {
                remain = k + remain;
            }
            if (remain == 0) {
                count += pr[remain] + 1;
            } else if (pr[remain] > 0) {
                count += pr[remain];
            }
            pr[remain]++;
        }
        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{4,5,0,-2,-3,1};
        int res = s.subarraysDivByK(nums, 5);
        System.out.println(res);
    }
}
