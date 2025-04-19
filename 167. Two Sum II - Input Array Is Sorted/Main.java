import java.util.*;


class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                break;
            }
            if (numbers[l] + numbers[r] < target) {
                l++;
                while (numbers[l] == numbers[l - 1] && l < r) {
                    l++;
                }
            } else {
                r--;
                while (numbers[r] == numbers[r + 1] && l < r) {
                    r--;
                }
            }
        }
        return new int[]{ l + 1, r + 1 };
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,7,11,15};
        int[] res = s.twoSum(nums, 9);
        System.out.println(Arrays.toString(res));
    }
}
