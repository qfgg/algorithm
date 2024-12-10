import java.util.*;

public class Main {
    public static int[] binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1, m;
        while (l < r) {
            m = (l + r) / 2;
            if (target == nums[m]) {
                return new int[]{m, m};
            }
            if (target < nums[m]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (target == nums[l]) {
            return new int[]{l, l};
        }
        if (target > nums[l]) {
            return new int[]{l, l + 1};
        }
        return new int[]{l - 1, l};
    }
    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 9, 10, 12, 19, 21, 24};
        int[] idx = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        Random r = new Random();
        int target = r.nextInt(30);
        System.out.print(Arrays.toString(nums));
        System.out.print(" ");
        System.out.println(target);
        System.out.print(" ");
        for (int i = 0; i < 8; i++) {
            System.out.print(idx[i]);
            System.out.print("  ");
            if (i > 2) {
                System.out.print(" ");
            }
        }
        System.out.println();
        int[] res = binarySearch(nums, target);
        System.out.println(Arrays.toString(res));
    }
}
