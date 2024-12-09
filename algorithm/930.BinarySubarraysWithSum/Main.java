import java.util.*;

public class Main {
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length, cnt = 0;
        int[] pre = new int[n];
        Map<Integer, Integer> sumCnt = new HashMap<>();
        pre[0] = nums[0];
        sumCnt.merge(pre[0], 1, Integer::sum);
        if (pre[0] == goal) {
            cnt++;
        }
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
            if (pre[i] == goal) {
                cnt++;
            }
            if (pre[i] >= goal && sumCnt.containsKey(pre[i] - goal)) {
                cnt += sumCnt.get(pre[i] - goal);
            }
            sumCnt.merge(pre[i], 1, Integer::sum);
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,0,1,0,1};
        int goal = 2;
        int res = numSubarraysWithSum(nums, goal);
        System.out.println(res);
    }
}
