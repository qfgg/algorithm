import java.util.*;


class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length, diff = Integer.MAX_VALUE, newDiff, l, r, sum = 0, newSum;
        for (int i = 0; i < len - 1; i++) {
            l = i + 1;
            r = len - 1;
            while (l < r) {
                newSum = nums[i] + nums[l] + nums[r];
                if (newSum == target) {
                    return target;
                }
                newDiff = Math.abs(newSum - target);
                if (newDiff < diff) {
                    diff = newDiff;
                    sum = newSum;
                }
                if (newSum > target) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{4,0,5,-5,3,3,0,-4,-5};
        int res = s.threeSumClosest(nums, -2);
        System.out.println(res);
    }
}
