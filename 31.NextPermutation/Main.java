import java.util.*;

class Solution {
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public void nextPermutation(int[] nums) {
        int n = nums.length, i, j;
        for (i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i > -1) {
            for (j = n - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }
        i++;
        j = n - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{10,6,7,1,6,4,9,6,5,1};
        s.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
