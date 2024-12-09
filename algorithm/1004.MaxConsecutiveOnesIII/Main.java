import java.util.*;

class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0, n = nums.length, max = 0, zCnt = 0;
        while (r < n) {
            if (nums[r] == 0) {
                if (zCnt < k) {
                    zCnt++;
                } else {
                    if (nums[l] == 1) {
                        while (l < r && nums[l] == 1) {
                            l++;
                        }
                    }
                    l++;
                }
            }
            if (r - l + 1 > max) {
                max = r - l + 1;
            }
            r++;
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;
        int res = s.longestOnes(nums, k);
        System.out.println(res);
    }
}
