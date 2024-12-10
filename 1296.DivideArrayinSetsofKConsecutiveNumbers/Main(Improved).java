import java.util.*;

class Solution {
    public void findEnds(Map<Integer, Integer> cnt, List<Integer> ends) {
        for (Integer i : cnt.keySet()) {
            if (cnt.get(i) > 0 &&
                    (!cnt.containsKey(i - 1) ||
                    !cnt.containsKey(i + 1) ||
                    cnt.get(i - 1) < cnt.get(i) ||
                    cnt.get(i + 1) < cnt.get(i))) {
                ends.add(i);
            }
        }
    }
    public int findConsecutive(Map<Integer, Integer> cnt, List<Integer> ends, int k) {
        int cur, i, gcnt = 0;
        for (Integer ed : ends) {
            cur = cnt.getOrDefault(ed, 0);
            if (cur == 0) {
                continue;
            }
            if (!cnt.containsKey(ed - 1) || cnt.get(ed - 1) < cur) {
                for (i = ed; i < ed + k; i++) {
                    cur = cnt.getOrDefault(i, 0);
                    if (cur == 0) {
                        return 0;
                    }
                    if (cur - 1 == 0) {
                        cnt.remove(i);
                    } else {
                        cnt.put(i, cur - 1);
                    }
                }
                gcnt++;
            } else if (!cnt.containsKey(ed + 1) || cnt.get(ed + 1) < cur) {
                for (i = ed; i > ed - k; i--) {
                    cur = cnt.getOrDefault(i, 0);
                    if (cur == 0) {
                        return 0;
                    }
                    if (cur - 1 == 0) {
                        cnt.remove(i);
                    } else {
                        cnt.put(i, cur - 1);
                    }
                }
                gcnt++;
            }
        }
        return gcnt;
    }
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length, i, gcnt = 0;
        if (n % k != 0) {
            return false;
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        for (i = 0; i < n; i++) {
            cnt.merge(nums[i], 1, Integer::sum);
        }
        List<Integer> ends;
        while (true) {
            ends = new ArrayList<>();
            findEnds(cnt, ends);
            if (ends.isEmpty()) {
                break;
            }
            gcnt += findConsecutive(cnt, ends, k);
        }
        return gcnt == n / k;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{5,7,8,8,7,4,3,6};
        int k = 1;
        boolean res = s.isPossibleDivide(nums, k);
        System.out.println(res);
    }
}
