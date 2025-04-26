import java.util.*;


class Solution {
    private void swap(int[] nums, int i1, int i2) {
        int tmp;
        tmp = nums[i1];
        nums[i1]  = nums[i2];
        nums[i2] = tmp;
    }
    public int firstMissingPositive(int[] nums) {
        int i = 0, n = nums.length, tmp, rightIdx;
        while (i < n) {
            rightIdx = nums[i] - 1;
            if (rightIdx < 0 || rightIdx >= n || nums[rightIdx] == nums[i]) {
                i++;
                continue;
            }
            swap(nums, rightIdx, i);
        }
        tmp = 1;
        for (i = 0; i < n; i++) {
            if (nums[i] != tmp) {
                break;
            }
            tmp++;
        }
        return tmp;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{99,94,96,11,92,5,91,89,57,85,66,63,84,81,79,61,74,78,77,30,64,13,58,18,70,69,51,12,32,34,9,43,39,8,1,38,49,27,21,45,47,44,53,52,48,19,50,59,3,40,31,82,23,56,37,41,16,28,22,33,65,42,54,20,29,25,10,26,4,60,67,83,62,71,24,35,72,55,75,0,2,46,15,80,6,36,14,73,76,86,88,7,17,87,68,90,95,93,97,98};
        int res = s.firstMissingPositive(nums);
        System.out.println(res);
    }
}
