import java.util.*;


class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length, i, j, l, r;
        long sum;
        for (i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                l = j + 1;
                r = len - 1;
                while (l < r) {
                    sum = (long)nums[i] + (long)nums[j] + (long)nums[l] + (long)nums[r];
                    if (sum == target) {
                        List<Integer> ans = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        res.add(ans);
                        l++;
                        while (nums[l] == nums[l - 1] && l < r) {
                            l++;
                        }
                    } else if (sum < target) {
                        l++;
                        while (nums[l] == nums[l - 1] && l < r) {
                            l++;
                        }
                    } else {
                        r--;
                        while (nums[r] == nums[r + 1] && l < r) {
                            r--;
                        }
                    }
                }
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1000000000,1000000000,1000000000,1000000000};
        List<List<Integer>> res = s.fourSum(nums, -294967296);
        System.out.println(res);
    }
}
