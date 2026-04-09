import java.util.*;


class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length, i, l = 0, r = n - 1;
        int[] ret = new int[n];
        for (i = 0; i < n; i++) {
            if (nums[i] > pivot) {
                ret[r--] = nums[i];
            } else if (nums[i] < pivot) {
                ret[l++] = nums[i];
            }
        }
        for (i = l; i <= r; i++) {
            ret[i] = pivot;
        }
        l = r + 1;
        r = n - 1;
        while (l < r) {
            i = ret[l];
            ret[l] = ret[r];
            ret[r] = i;
            l++;
            r--;
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{9,12,5,10,14,3,10};
        int pivot = 10;
        int[] res = s.pivotArray(nums, pivot);
        System.out.println(Arrays.toString(res));
    }
}
