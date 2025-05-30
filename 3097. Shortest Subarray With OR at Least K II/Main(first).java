import java.util.*;


class Solution {
    int searchRightBorder(int[] nums, int k, int min, int rightStart, List<Integer> suffixOr) {
        List<Integer> prefixOr = new ArrayList<>();
        int i, cur = 0, pn, sn, curMin = min, l, r, m, max = Math.min(nums.length, rightStart + min - 2);
        for (i = rightStart; i < max; i++) {
            cur = cur | nums[i];
            prefixOr.add(cur);
        }
        sn = suffixOr.size();
        pn = prefixOr.size();
        for (i = 0; i < pn; i++) {
            curMin = Math.min(curMin, i + 1 + sn);
            l = 0;
            r = sn;
            while (l < r) {
                m = (l + r) / 2;
                if ((prefixOr.get(i) | suffixOr.get(m)) >= k) {
                    curMin = Math.min(curMin, m + i + 2);
                    r = m;
                } else {
                    l = m + 1;
                }
            }
        }
        return curMin;
    }
    int findMin(int[] nums, int k, int pos) {
        if (pos == nums.length - 1) {
            if (nums[pos] >= k) {
                return 1;
            }
            return Integer.MAX_VALUE;
        }
        int i, n = nums.length, cur = 0, border, min;
        for (i = pos; i < n; i++) {
            cur = cur | nums[i];
            if (cur >= k) {
                break;
            }
        }
        if (i == n) {
            return Integer.MAX_VALUE;
        }
        border = i;
        List<Integer> suffixOr = new ArrayList<>();
        cur = 0;
        for (i = border; i >= 0; i--) {
            cur = cur | nums[i];
            suffixOr.add(cur);
            if (cur >= k) {
                break;
            }
        }
        min = border - i + 1;
        if (border < n - 1) {
            min = searchRightBorder(nums, k, min, border + 1, suffixOr);
            min = Math.min(min, findMin(nums, k, border + 1));
        }
        return min;
    }
    public int minimumSubarrayLength(int[] nums, int k) {
        int ret = findMin(nums, k, 0);
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{32,1,2,81,76,58};
        int res = s.minimumSubarrayLength(nums, 125);
        System.out.println(res);
    }
}
