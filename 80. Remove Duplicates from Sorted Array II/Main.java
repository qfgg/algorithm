import java.util.*;


class Solution {
    public int removeDuplicates(int[] nums) {
        int cur = nums[0], count = 0, i, n = nums.length, k = 0, nextPos = 0;
        for (i = 0; i < n; i++) {
            if (count < 2) {
                nums[nextPos] = cur;
                nextPos++;
                count++;
                k++;
            }
            if (i == n - 1 || nums[i] != nums[i + 1]) {
                if (i < n - 1) {
                    cur = nums[i + 1];
                }
                count = 0;
            }
        }
        return k;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3};
        int res = slt.removeDuplicates(nums);
        System.out.println(res);
    }
}
