import java.util.*;


class Solution {
    private void swapHeadWithTail(int[] nums, int start, int end, int l) {
        int tmp, head = start + l - 1, tail = end;
        while (l > 0) {
            tmp = nums[head];
            nums[head] = nums[tail];
            nums[tail] = tmp;
            head--;
            tail--;
            l--;
        }
    }
    private void rotateLeftPatial(int[] nums, int start, int end, int k) {
        int n = end - start + 1, half = n / 2;
        if (k == half && n % 2 == 0) {
            swapHeadWithTail(nums, start, end, k);
            return;
        }
        if (k <= half) {
            swapHeadWithTail(nums, start, end, k);
            rotateLeftPatial(nums, start, end - k, k);
        } else {
            rotateRightPatial(nums, start, end, n - k);
        }
    }
    private void rotateRightPatial(int[] nums, int start, int end, int k) {
        int n = end - start + 1, half = n / 2;
        if (k == half && n % 2 == 0) {
            swapHeadWithTail(nums, start, end, k);
            return;
        }
        if (k <= half) {
            swapHeadWithTail(nums, start, end, k);
            rotateRightPatial(nums, start + k, end, k);
        } else {
            rotateLeftPatial(nums, start, end, n - k);
        }
    }
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (nums.length == 1 || k == 0) {
            return;
        }
        rotateRightPatial(nums, 0, nums.length - 1, k);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();
        int[] nums = new int[]{1,2};
        int k = 3;
        sl.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
