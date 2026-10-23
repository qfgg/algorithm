import java.util.*;


class Solution {
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        int i, n = nums.length, remain = k, max;
        if (nums[0] > 1) {
            max = Math.min(remain, nums[0] - 1);
            sum = (long)(1 + max) * max / 2;
            remain -= max;
        }
        i = 0;
        while (remain > 0) {
            if (i + 1 < n) {
                max = Math.min(remain, nums[i + 1] - nums[i] - 1);
            } else {
                max = remain;
            }
            if (max > 0) {
                sum += (long)(nums[i] + 1 + nums[i] + max) * max / 2;
                remain -= max;
            }
            i++;
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int[] nums = new int[]{5,6};
        int k = 6;
        long ret = slt.minimalKSum(nums, k);
        System.out.println(ret);
    }
}
