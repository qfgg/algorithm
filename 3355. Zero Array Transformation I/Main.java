import java.util.*;

class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length, qn = queries.length, i;
        int[][] sum = new int[n][2];
        for (i = 0; i < qn; i++) {
            sum[queries[i][0]][0]++;
            sum[queries[i][1]][1]++;
        }
        int cur = 0, toDel = 0;
        for (i = 0; i < n; i++) {
            if (toDel > 0) {
                cur = cur - toDel;
                toDel = 0;
            }
            if (sum[i][0] > 0) {
                cur = cur + sum[i][0];
            }
            if (sum[i][1] > 0) {
                toDel += sum[i][1];
            }
            if (nums[i] > cur) {
                return false;
            }
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 1,3,1,0,1,1,6,7,3 };
        int[][] queries = {{1,1},{1,7},{1,5},{1,8},{3,7},{0,2},{4,6},{5,6},{2,8}};
        boolean res = s.isZeroArray(nums, queries);
        System.out.println(res);
    }
}
// 1 5 5 5 6 7 6 4 2
