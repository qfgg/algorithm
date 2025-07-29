import java.util.*;


class Solution {
    public int[] rearrangeArray(int[] nums) {
        int pos = 0, neg = 0, n = nums.length, i = 0;
        int[] res = new int[n];
        boolean isPos = true;
        while (i < n) {
            if (isPos) {
                while (pos < n && nums[pos] < 0) {
                    pos++;
                }
                res[i] = nums[pos];
                pos++;
                i++;
                isPos = false;
            } else {
                while (neg < n && nums[neg] > 0) {
                    neg++;
                }
                res[i] = nums[neg];
                neg++;
                i++;
                isPos = true;
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{3,1,-2,-5,2,-4};
        int[] res = s.rearrangeArray(nums);
        System.out.println(Arrays.toString(res));
    }
}
