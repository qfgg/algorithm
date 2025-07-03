import java.util.*;


class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length, i, count = 0;
        int[] pre = new int[n];
        int[] cnt = new int[n + 1];
        pre[0] = nums[0] % 2;
        if (k == 1 && pre[0] == 1) {
            count++;
        }
        cnt[pre[0]]++;
        for (i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + (nums[i] % 2);
            if (pre[i] == k) {
                count++;
            }
            if (pre[i] >= k) {
                count = count + cnt[pre[i] - k];
            }
            cnt[pre[i]]++;
        }
        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,2,2,1,2,2,1,2,2,2};
        int res = s.numberOfSubarrays(nums, 2);
        System.out.println(res);
    }
}
