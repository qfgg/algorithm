import java.util.*;


class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length, i, max = nums[0];
        for (i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
        }
        int[] minDv = new int[max + 1];
        minDv[1] = 1;
        int multiple, cnt = 0;
        for (i = 2; i <= max; i++) {
            if (minDv[i] == 0) {
                minDv[i] = 1;
            }
            multiple = i * 2;
            while (multiple <= max) {
                if (minDv[multiple] == 0) {
                    minDv[multiple] = i;
                }
                multiple += i;
            }
        }
        for (i = n - 2; i >= 0; i--) {
            while (nums[i] > nums[i + 1] && minDv[nums[i]] > 1) {
                nums[i] = minDv[nums[i]];
                cnt++;
            }
            if (nums[i] > nums[i + 1]) {
                return -1;
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{59,73,97,181,229};
        int res = s.minOperations(nums);
        System.out.println(res);
    }
}
