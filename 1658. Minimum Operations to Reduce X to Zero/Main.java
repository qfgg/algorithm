import java.util.*;


class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length, i, subtract, minlen = n, ps;
        int[] pre = new int[n];
        HashMap<Integer, Integer> sum2i = new HashMap<>();
        for (i = 0; i < n; i++) {
            pre[i] = i == 0 ? nums[i] : pre[i - 1] + nums[i];
        }
        if (pre[n - 1] == x) {
            return n;
        }
        if (pre[n - 1] < x) {
            return -1;
        }
        subtract = pre[n - 1] - x;
        for (i = 0; i < n; i++) {
            if (pre[i] == subtract) {
                minlen = Math.min(minlen, n - i - 1);
            } else if (pre[i] > subtract) {
                ps = pre[i] - subtract;
                if (sum2i.containsKey(ps)) {
                    minlen = Math.min(minlen, n - i + sum2i.get(ps));
                }
            }
            sum2i.put(pre[i], i);
        }
        return minlen == n ? -1 : minlen;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{3,2,20,1,1,3};
        int x = 10;
        int res = s.minOperations(nums, x);
        System.out.println(res);
    }
}
