import java.util.*;


public class Main {
    public static int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid;
        while (start <= end) {
            if (start == end) {
                return nums[start] == target ? start : -1;
            }
            mid = (start + end) / 2;
            if (nums[mid] >= nums[start]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int ans = search(nums, 3);
        System.out.println(ans);
    }
}
