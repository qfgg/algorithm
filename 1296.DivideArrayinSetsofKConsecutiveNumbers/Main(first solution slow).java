import java.util.*;

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length, i, j, m = 0, curMin = 0, ct, pre;
        boolean hasChanged = false;
        if (n % k != 0) {
            return false;
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        for (i = 0; i < n; i++) {
            cnt.merge(nums[i], 1, Integer::sum);
        }
        Arrays.sort(nums);
        i = n / k;
        while (i > 0) {
            if (hasChanged) {
                m = curMin;
                hasChanged = false;
            }
            pre = m;
            for (j = 0; j < k; j++) {
                if (m == n || cnt.get(nums[m]) == 0 || (pre != m && nums[m] - nums[pre] != 1)) {
                    return false;
                }
                ct = cnt.get(nums[m]);
                cnt.put(nums[m], ct - 1);
                pre = m;
                if (ct > 1 && !hasChanged) {
                    curMin = m;
                    hasChanged = true;
                }
                while (m < n && nums[m] == nums[pre]) {
                    m++;
                }
            }
            i--;
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{15,16,17,18,19,16,17,18,19,20,6,7,8,9,10,3,4,5,6,20};
        int k = 5;
        boolean res = s.isPossibleDivide(nums, k);
        System.out.println(res);
    }
}
