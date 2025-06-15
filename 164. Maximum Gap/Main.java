import java.util.*;


class Solution {
    int getMax (int[] nums) {
        int n = nums.length, i, max = nums[0];
        for (i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }
    void countSortByDigit(int[] nums, int exp) {
        int n = nums.length, i, digit;
        int[] count = new int[10];
        int[] copy = new int[n];
        for (i = 0; i < n; i++) {
            digit = (nums[i] / exp) % 10;
            count[digit]++;
        }
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (i = n - 1; i >= 0; i--) {
            digit = (nums[i] / exp) % 10;
            copy[count[digit] - 1] = nums[i];
            count[digit]--;
        }
        for (i = 0; i < n; i++) {
            nums[i] = copy[i];
        }
    }
    public int maximumGap(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }
        int max = getMax(nums), exp = 1, maxGap = 0, i, n = nums.length;
        while (max / exp > 0) {
            countSortByDigit(nums, exp);
            exp = exp * 10;
        }
        for (i = 1; i < n; i++) {
            maxGap = Math.max(maxGap, Math.abs(nums[i] - nums[i - 1]));
        }
        return maxGap;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{170, 45, 75, 90, 802, 24, 2, 66};
        int res = s.maximumGap(nums);
        System.out.println(res);
    }
}
