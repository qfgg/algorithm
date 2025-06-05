import java.util.*;


class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int i, sum = 0, min = Integer.MAX_VALUE;
        for (i = 0; i < n; i++) {
            sum += nums[i];
            min = Math.min(min, nums[i]);
        }
        return sum - n * min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{6,9,1};
        int res = s.minMoves(nums);
        System.out.println(res);
    }
}
