import java.util.*;


class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int n = nums.length, i = 0, j = 0, pd = 1, npd, num = 0;
        while (i <= j && j < n) {
            npd = pd * nums[j];
            if (npd < k) {
                pd = npd;
                num += j - i + 1;
                j++;
            } else {
                if (i < j) {
                    pd = pd / nums[i];
                    i++;
                } else {
                    i++;
                    j = i;
                }
            }
        }
        return num;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{76,8,75,22};
        int res = s.numSubarrayProductLessThanK(nums, 18);
        System.out.println(res);
    }
}
