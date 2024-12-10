import java.util.*;


class Main {
    public static int partitionDisjoint(int[] nums) {
        int len = nums.length;
        int curMax = nums[0];
        int leftMax = nums[0];
        int leftLen = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] < leftMax) {
                leftLen = i + 1;
                leftMax = curMax;
            } else {
                if (nums[i] > curMax) {
                    curMax = nums[i];
                }
            }
        }
        return leftLen;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,0,3,8,6};
        int ans = partitionDisjoint(nums);
        System.out.println(ans);
    }
}
