import java.util.*;


class Solution {
    boolean binarySearch (int[] nums, int start, int end, int target) {
        if (start > end) {
            return false;
        }
        if (start == end) {
            return nums[start] == target;
        }
        int l = start, r = end + 1, m;
        while (l < r) {
            m = (l + r) / 2;
            if (nums[m] == target) {
                return true;
            }
            if (target < nums[m]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return false;
    }
    boolean find(int[] nums, int start, int end, int target) {
        if (start > end) {
            return false;
        }
        if (start == end) {
            return nums[start] == target;
        }
        int m = (start + end + 1) / 2;
        if (nums[m] == target) {
            return true;
        }
        if (nums[m] < nums[start]) {
            return find(nums, start, m - 1, target) || binarySearch(nums, m + 1, end, target);
        }
        if (nums[m] > nums[start]) {
            return binarySearch(nums, start, m - 1, target) || find(nums, m + 1, end, target);
        }
        if (nums[start] == nums[end]) {
            return find(nums, start + 1, end - 1, target);
        }
        if (nums[start] > nums[end]) {
            return binarySearch(nums, start, m - 1, target) || find(nums, m + 1, end, target);
        }
        return binarySearch(nums, start, end, target);
    }
    public boolean search(int[] nums, int target) {
        return find(nums, 0, nums.length - 1, target);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,1,1,1,1,1,1,1,1,13,1,1,1,1,1,1,1,1,1,1,1,1};
        boolean res = s.search(nums, 13);
        System.out.println(res);
    }
}
