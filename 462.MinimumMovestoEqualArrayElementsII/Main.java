import java.util.*;


class Main {
    public static int minMoves2(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int stop = (len + 1) / 2;
        int i = 0;
        int j = len -1;
        int ans = 0;
        while (j >= stop) {
            ans += nums[j] - nums[i];
            i++;
            j--;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,10,2,9};
        int ret = minMoves2(nums);
        System.out.println(ret);
    }
}
