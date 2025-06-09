import java.util.*;


class Solution {
    public int majorityElement(int[] nums) {
        int i, j, n = nums.length, mj = 0, half = n / 2;
        int[] bitCount = new int[32];
        for (i = 0; i < n; i++) {
            for (j = 0; j < 32; j++) {
                if (((nums[i] >>> j) & 1) > 0) {
                    bitCount[j]++;
                }
            }
        }
        for (j = 0; j < 32; j++) {
            if (bitCount[j] > half) {
                mj = mj | (1 << j);
            }
        }
        return mj;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1000000000,1000000000,-1000000000,-1000000000,-1000000000};
        int res = s.majorityElement(nums);
        System.out.println(res);
    }
}
