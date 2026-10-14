import java.util.*;


class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int i, n = nums.length, cnt = 1, l = nums[0];
        for (i = 0; i < n; i++) {
            if (nums[i] > l + k) {
                cnt++;
                l = nums[i];
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int[] nums = new int[]{5,2,1,5,3};
        int k = 3;
        int ret = slt.partitionArray(nums, k);
        System.out.println(ret);
    }
}
