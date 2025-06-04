import java.util.*;


class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length, i;
        if (n < 3) {
            return false;
        }
        int[] pmin = new int[n];
        int maxIdx;
        pmin[0] = 0;
        for (i = 1; i < n; i++) {
            if (nums[pmin[i - 1]] < nums[i]) {
                pmin[i] = pmin[i - 1];
            } else {
                pmin[i] = i;
            }
        }
        maxIdx = n - 1;
        for (i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[maxIdx]) {
                if (pmin[i] != i) {
                    return true;
                }
            } else if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,1,5,0,4,6};
        boolean res = s.increasingTriplet(nums);
        System.out.println(res);
    }
}
