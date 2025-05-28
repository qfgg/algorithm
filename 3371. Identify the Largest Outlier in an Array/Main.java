import java.util.*;


class Solution {
    public int getLargestOutlier(int[] nums) {
        int n = nums.length, i, max = Integer.MIN_VALUE;
        int[] presum = new int[n];
        HashMap<Integer, HashSet<Integer>> valIndices = new HashMap<>();
        presum[0] = nums[0];
        HashSet<Integer> set = new HashSet<>(List.of(0));
        valIndices.put(nums[0], set);
        for (i = 1; i < n; i++) {
            presum[i] = presum[i - 1] + nums[i];
            if (valIndices.containsKey(nums[i])) {
                valIndices.get(nums[i]).add(i);
            } else {
                set = new HashSet<>(List.of(i));
                valIndices.put(nums[i], set);
            }
        }
        int total = presum[n - 1], rest, halfRest;
        for (i = 0; i < n; i++) {
            rest = total - nums[i];
            halfRest = rest / 2;
            if (halfRest * 2 == rest && valIndices.containsKey(halfRest)) {
                set = valIndices.get(halfRest);
                if (!set.contains(i) || set.size() > 1) {
                    max = Math.max(max, nums[i]);
                }
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{-2,-1,-3,-6,4};
        int res = s.getLargestOutlier(nums);
        System.out.println(res);
    }
}
