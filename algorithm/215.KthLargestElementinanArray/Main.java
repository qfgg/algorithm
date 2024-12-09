import java.util.*;


public class Main {
    public static int quickSelect(int[] nums, int k, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int i = l, j = r, pivot;
        Random rand = new Random();
        int p = rand.nextInt(r - l + 1) + l;
        pivot = nums[p];
        nums[p] = nums[r];
        while (true) {
            while (i < j) {
                if (nums[i] > pivot) {
                    nums[j] = nums[i];
                    j--;
                    break;
                }
                i++;
            }
            while (i < j) {
                if (nums[j] < pivot) {
                    nums[i] = nums[j];
                    i++;
                    break;
                }
                j--;
            }
            if (i == j) {
                nums[i] = pivot;
                break;
            }
        }
        if (r - i + 1 == k) {
            return nums[i];
        }
        if (r - i + 1 > k) {
            return quickSelect(nums, k, i + 1, r);
        }
        return quickSelect(nums, k - r + i - 1, l, i - 1);
    }
    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k, 0, nums.length - 1);
    }
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        int k = 4;
        int ans = findKthLargest(nums, k);
        System.out.println(ans); // 4
    }
}
