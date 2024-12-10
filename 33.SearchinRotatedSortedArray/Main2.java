import java.util.*;


public class Main {
    public static int binarySearch(int[] nums, int target, int start, int end) {
        if (start > end || (start == end && nums[start] != target)) {
            return -1;
        }
        int mid = (end + start) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (target < nums[mid]) {
            return binarySearch(nums, target, start, mid - 1);
        }
        return binarySearch(nums, target, mid + 1, end);
    }
    public static int searchRange(int[] nums, int target, int start, int end) {
        if (start > end || (start == end && nums[start] != target)) {
            return -1;
        }
        int mid = (end + start) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] >= nums[start]) {
            if (nums[start] <= target && target < nums[mid]) {
                return binarySearch(nums, target, start, mid - 1);
            }
            return searchRange(nums, target, mid + 1, end);
        }
        if (nums[mid] < target && target <= nums[end]) {
            return binarySearch(nums, target, mid + 1, end);
        }
        return searchRange(nums, target, start, mid - 1);
    }
    public static int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }
        return searchRange(nums, target, 0, len - 1);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int ans = search(nums, 0);
        System.out.println(ans);
    }
}
