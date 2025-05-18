import java.util.*;


class Solution {
    private int modAdd(long base, long add, long times, int mod) {
        long product = 0;
        while (times > 0) {
            if ((times & 1) == 1) {
                product = (product + add) % mod;
            }
            add = (add * 2) % mod;
            times = times >> 1;
        }
        return (int)(base + product) % mod;
    }
    private int makeSubs(long[] cur, int src, int num, int sum, int mod, HashMap<Integer, long[]> head2Subs) {
        if (head2Subs.containsKey(src)) {
            long[] srcSumCount;
            srcSumCount = head2Subs.get(src);
            cur[1] = (cur[1] + srcSumCount[1]) % mod;
            int add = modAdd(srcSumCount[0], num, srcSumCount[1], mod);
            cur[0] = modAdd(cur[0], add, 1, mod);
            sum = modAdd(sum, add, 1, mod);
        }
        return sum;
    }
    public int sumOfGoodSubsequences(int[] nums) {
        HashMap<Integer, long[]> head2Subs = new HashMap<>();
        int i, n = nums.length, sum = 0, mod = 1000000007;
        long[] cur;
        for (i = n - 1; i >= 0; i--) {
            if (head2Subs.containsKey(nums[i])) {
                cur = head2Subs.get(nums[i]);
                cur[1] = (cur[1] + 1) % mod;
                cur[0] = modAdd(cur[0], nums[i], 1, mod);
            } else {
                cur = new long[]{nums[i], 1};
                head2Subs.put(nums[i], cur);
            }
            sum = modAdd(sum, nums[i], 1, mod);
            sum = makeSubs(cur, nums[i] + 1, nums[i], sum, mod, head2Subs);
            sum = makeSubs(cur, nums[i] - 1, nums[i], sum, mod, head2Subs);
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{3,4,5};
        int res = s.sumOfGoodSubsequences(nums);
        System.out.println(res);
    }
}
