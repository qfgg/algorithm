import java.util.*;


class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, n = nums.length, i, part;
        for (i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) {
            return false;
        }
        part = sum / k;
        for (i = 0; i < n; i++) {
            if (nums[i] > part) {
                return false;
            }
        }
        int higherBound = 1 << n, j, bit, preBit, curRemain;
        Set<Integer>[] remain = new Set[higherBound];
        remain[0] = new HashSet<>();
        remain[0].add(0);
        for (i = 1; i < higherBound; i++) {
            remain[i] = new HashSet<>();
            bit = 1;
            for (j = 0; j < n && bit <= i; j++) {
                bit = 1 << j;
                if ((i & bit) > 0) {
                    preBit = i ^ bit;
                    for (Integer r : remain[preBit]) {
                        curRemain = r + nums[j];
                        if (curRemain <= part) {
                            remain[i].add(curRemain % part);
                        }
                    }
                }
            }
        }
        return remain[higherBound - 1].contains(0);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int k = 5;
        int[] nums = new int[]{3522,181,521,515,304,123,2512,312,922,407,146,1932,4037,2646,3871,269};
        boolean res = s.canPartitionKSubsets(nums, k);
        System.out.println(res);
    }
}
