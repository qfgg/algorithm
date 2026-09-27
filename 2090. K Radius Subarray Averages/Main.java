import java.util.*;


class Solution {
    public int[] getAverages(int[] nums, int k) {
        long sum = 0;
        int n = nums.length, i, j, l, r, len;
        int[] ret = new int[n];
        for (i = 0; i < k && i < n; i++) {
            ret[i] = -1;
            ret[n - 1 - i] = -1;
        }
        i = k;
        l = 0;
        r = k * 2;
        len = r + 1;
        for (j = l; j <= r && r < n; j++) {
            sum += nums[j];
            ret[i] = (int)(sum / len);
        }
        l++;
        r++;
        i++;
        while (r < n) {
            sum = sum + nums[r] - nums[l - 1];
            ret[i] = (int)(sum / len);
            l++;
            r++;
            i++;
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int[] nums = new int[]{8};
        int k = 3;
        int[] ret = slt.getAverages(nums, k);
        System.out.println(Arrays.toString(ret));
    }
}