import java.util.*;


class Solution {
    void swap(int[] nums,  int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
    public void sortColors(int[] nums) {
        int n = nums.length, l2 = 0, r0 = n - 1, l1 = -1, r1 = -1;
        while (l2 <= r0) {
            while (l2 <= r0 && nums[l2] == 0) {
                l2++;
            }
            if (l2 > r0) {
                break;
            }
            if (nums[l2] == 1) {
                if (l1 == -1) {
                    l1 = l2;
                }
                l2++;
                while (l2 <= r0 && nums[l2] != 2) {
                    if (nums[l2] == 0) {
                        swap(nums, l1, l2);
                        l1++;
                    }
                    l2++;
                }
            }
            if (l2 > r0) {
                break;
            }
            while (r0 >= l2 && nums[r0] == 2) {
                r0--;
            }
            if (l2 > r0) {
                break;
            }
            if (nums[r0] == 1) {
                if (r1 == -1) {
                    r1 = r0;
                }
                r0--;
                while (r0 >= l2 && nums[r0] != 0) {
                    if (nums[r0] == 2) {
                        swap(nums, r1, r0);
                        r1--;
                    }
                    r0--;
                }
            }
            if (l2 > r0) {
                break;
            }
            swap(nums, l2, r0);
            if (l1 != -1 && l1 < l2) {
                swap(nums, l1, l2);
                l1++;
            }
            if (r1 != -1 && r1 > r0) {
                swap(nums, r1, r0);
                r1--;
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{0};
        s.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
