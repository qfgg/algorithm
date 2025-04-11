import java.util.*;

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int[] count = new int[100001];
        int max = nums[0], min = nums[0], i, center, right, left, sum = 0, maxf = 1;
        for (int num : nums) {
            count[num]++;
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        right = min + k;
        for (i = min + 1; i <= right && i <= max; i++) {
            if (count[i] > 0) {
                sum += count[i];
            }
        }
        maxf = Math.max(maxf, count[min] + Math.min(numOperations , sum));
        for (center = min + 1; center <= max; center++) {
            left = center - k - 1;
            sum -= count[center];
            sum += count[center - 1];
            if (center <= max - k) {
                sum += count[center + k];
            }
            if (left >= min) {
                sum -= count[left];
            }
            maxf = Math.max(maxf, count[center] + Math.min(numOperations , sum));
        }
        return maxf;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{18,57};
        int k = 97, numOperations = 2;
        int res = s.maxFrequency(nums, k, numOperations);
        System.out.println(res);
    }
}
