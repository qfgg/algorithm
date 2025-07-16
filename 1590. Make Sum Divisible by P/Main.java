import java.util.*;


class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length, i, remain = 0, wholeRemain, min = n, preRemain;
        HashMap<Integer, Integer> r2i = new HashMap<>();
        wholeRemain = nums[0] % p;
        for (i = 1; i < n; i++) {
            wholeRemain = (wholeRemain + nums[i]) % p;
        }
        if (wholeRemain == 0) {
            return 0;
        }
        for (i = 0; i < n; i++) {
            remain = (remain + nums[i]) % p;
            if (remain == wholeRemain) {
                min = Math.min(min, i + 1);
            }
            preRemain = remain - wholeRemain;
            if (preRemain < 0) {
                preRemain += p;
            }
            if (r2i.containsKey(preRemain)) {
                min = Math.min(min, i - r2i.get(preRemain));
            }
            r2i.put(remain, i);
        }
        return min == n ? -1 : min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{6,3,5,2};
        int p = 9;
        int res = s.minSubarray(nums, p);
        System.out.println(res);
    }
}
