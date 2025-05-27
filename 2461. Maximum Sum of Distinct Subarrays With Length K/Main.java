import java.util.*;


class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long max = 0, cursum = 0;
        int[] count = new int[100001];
        int i, j, n = nums.length, countMore = 0;
        for (i = 0; i < k; i++) {
            count[nums[i]]++;
            if (count[nums[i]] == 2) {
                countMore++;
            }
            cursum += nums[i];
        }
        if (countMore == 0) {
            max = Math.max(max, cursum);
        }
        i = 1;
        j = k;
        while (j < n) {
            count[nums[j]]++;
            if (count[nums[j]] == 2) {
                countMore++;
            }
            count[nums[i - 1]]--;
            if (count[nums[i - 1]] == 1) {
                countMore--;
            }
            cursum -= nums[i - 1];
            cursum += nums[j];
            if (countMore == 0) {
                max = Math.max(max, cursum);
            }
            i++;
            j++;
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,2,3,3};
        long res = s.maximumSubarraySum(nums, 1);
        System.out.println(res);
    }
}
