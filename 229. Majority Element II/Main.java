import java.util.*;


class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 1) {
            res.add(nums[0]);
            return res;
        } else if (nums.length == 2) {
            if (nums[0] == nums[1]) {
                res.add(nums[0]);
            } else {
                res.add(nums[0]);
                res.add(nums[1]);
            }
            return res;
        }
        int n = nums.length, i, count1 = 0, count2 = 0, mj1 = Integer.MAX_VALUE, mj2 = Integer.MAX_VALUE;
        for (i = 0; i < n; i++) {
            if (mj1 == nums[i]) {
                count1++;
            } else if (mj2 == nums[i]) {
                count2++;
            } else if (count1 == 0) {
                mj1 = nums[i];
                count1++;
            } else if (count2 == 0) {
                mj2 = nums[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (i = 0; i < n; i++) {
            if (nums[i] == mj1) {
                count1++;
            } else if (nums[i] == mj2) {
                count2++;
            }
        }
        if (count1 > n / 3) {
            res.add(mj1);
        }
        if (count2 > n / 3) {
            res.add(mj2);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,1,1,3,1,4,5,6};
        List<Integer> res = s.majorityElement(nums);
        System.out.println(res);
    }
}
