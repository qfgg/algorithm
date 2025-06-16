import java.util.*;


class Solution {
    int[] dfs(int[] nums, int n, int add, int divide) {
        int xor0 = 0, xor1 = 0, i;
        for (i = 0; i < n; i++) {
            if ((nums[i] + add) % divide == 0) {
                if ((nums[i] + add) / divide % 2 == 0) {
                    xor0 = xor0 ^ nums[i];
                } else {
                    xor1 = xor1 ^ nums[i];
                }
            }
        }
        if (xor0 != 0 && xor1 != 0) {
            return new int[]{ xor0, xor1 };
        }
        if (xor0 != 0) {
            return dfs(nums, n, add, divide * 2);
        }
        return dfs(nums, n, add + divide, divide * 2);
    }
    public int[] singleNumber(int[] nums) {
        int n = nums.length, i;
        if (n == 2) {
            return nums;
        }
        int cnt0 = 0;
        for (i = 0; i < n; i++) {
            if (nums[i] == 0) {
                cnt0++;
            }
        }
        if (cnt0 == 1) {
            int xor = 0;
            for (i = 0; i < n; i++) {
                xor = xor ^ nums[i];
            }
            return new int[]{0, xor};
        }
        return dfs(nums, n, 0, 1);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{7,1,2,7,1,3,2,5,4,4};
        int[] res = s.singleNumber(nums);
        System.out.println(Arrays.toString(res));
    }
}
