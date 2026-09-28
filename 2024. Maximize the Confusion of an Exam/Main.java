import java.util.*;


class Solution {
    int replace(List<Integer> nums, int k, int start) {
        int l = start, r = start, n = nums.size(), sum = r > 0 ? nums.get(r - 1) : 0, max = 0, remain = k;
        while (r < n) {
            if (remain < nums.get(r)) {
                sum = sum + remain;
                max = Math.max(max, sum);
                sum = sum - remain;
                remain += nums.get(l);
                sum = sum - nums.get(l) - (l > 0 ? nums.get(l - 1) : 0);
                l += 2;
            } else {
                remain -= nums.get(r);
                sum += nums.get(r) + (r < n - 1 ? nums.get(r + 1) : 0);
                max = Math.max(max, sum);
                r += 2;
                if (r >= n && l - 2 >= 0) {
                    max = Math.max(max, sum + remain);
                }
            }
        }
        return max;
    }
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int i, l = answerKey.length(), cnt = 1;
        if (l == 1) {
            return 1;
        }
        List<Integer> nums = new ArrayList<>();
        for(i = 1; i < l; i++) {
            if (answerKey.charAt(i) != answerKey.charAt(i - 1)) {
                nums.add(cnt);
                cnt = 1;
            } else {
                cnt++;
            }
        }
        nums.add(cnt);
        if (nums.size() == 1) {
            return l;
        }
        return Math.max(replace(nums, k, 0), replace(nums, k, 1));
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        String answerKey = "TTFTTFTTFF";
        int k = 3;
        int ret = slt.maxConsecutiveAnswers(answerKey, k);
        System.out.println(ret);
    }
}
