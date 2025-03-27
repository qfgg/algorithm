import java.util.*;

class Solution {
    private boolean canClear(int[] nums, int[][] queries, int index) {
        int[] diff = new int[nums.length + 1];
        for (int i = 0; i <= index; i++) {
            diff[queries[i][0]] += queries[i][2];
            diff[queries[i][1] + 1] -= queries[i][2];
        }
        int diffSum = 0;
        for (int i = 0; i < nums.length; i++) {
            diffSum += diff[i];
            if (nums[i] > diffSum) {
                return false;
            }
        }
        return true;
    }
    public int minZeroArray(int[] nums, int[][] queries) {
        int i;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                break;
            }
        }
        if (i == nums.length) {
            return 0;
        }
        int l = 0, r = queries.length, mid, ret = -1;
        while (l < r) {
            mid = (l + r) / 2;
            if (canClear(nums, queries, mid)) {
                ret = mid + 1;
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 7,6,8 };
        int[][] queries = {{0,0,2},{0,1,5},{2,2,5},{0,2,4}};
        int res = s.minZeroArray(nums, queries);
        System.out.println(res);
    }
}
