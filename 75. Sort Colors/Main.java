import java.util.*;


class Solution {
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1, i, tmp;
        while (l < r) {
            if (nums[l] == 0) {
                l++;
                continue;
            }
            if (nums[r] == 2) {
                r--;
                continue;
            }
            if (nums[l] == 1 && nums[r] == 1) {
                i = l + 1;
                while (i < r) {
                    if (nums[i] == 0) {
                        nums[l] = 0;
                        nums[i] = 1;
                        l++;
                        break;
                    }
                    if (nums[i] == 2) {
                        nums[r] = 2;
                        nums[i] = 1;
                        r--;
                        break;
                    }
                    i++;
                }
                if (i == r) {
                    break;
                }
            } else {
                tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                if (nums[l] == 0) {
                    l++;
                }
                if (nums[r] == 2) {
                    r--;
                }
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,2,0,2,2,1,1,0,1,0,2};
        s.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
