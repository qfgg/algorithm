import java.util.*;


class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long total = 0, cnt = 0;
        int i, len = nums.length;
        for (i = 0; i < len; i++) {
            if (nums[i] == 0) {
                cnt++;
            } else {
                if (cnt != 0) {
                    total += cnt * (cnt + 1) / 2;
                    cnt = 0 ;
                }
            }
        }
        if (cnt != 0) {
            total += cnt * (cnt + 1) / 2;
        }
        return total;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{0,0,0,1,3,0,0,2,0,0,4,0};
        long ret = s.zeroFilledSubarray(nums);
        System.out.println(ret);
    }
}
