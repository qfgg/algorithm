import java.util.*;


class Main {
    public static int partitionDisjoint(int[] nums) {
        int i;
        int len = nums.length;
        int max = nums[0];
        int[] min = new int[len];
        min[len - 1] = nums[len - 1];
        for (i = len - 2; i >=0; i--) {
            min[i] = nums[i] < min[i + 1] ? nums[i] : min[i + 1];
        }
        for (i = 0; i < len - 1; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (max <= min[i + 1]) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,0,3,8,6};
        int ans = partitionDisjoint(nums);
        System.out.println(ans);
    }
}
