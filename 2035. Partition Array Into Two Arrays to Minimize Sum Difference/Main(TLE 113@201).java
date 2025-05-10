import java.util.*;


class Solution {
    private int searchStartIdx(int[] presum, int target, int m) {
        int l = 0, r = m + 1, mid, sum, lastLess = 0;
        while (l < r) {
            mid = (l + r) / 2;
            sum = mid > 0 ? presum[mid + m - 1] - presum[mid - 1] : presum[mid + m - 1];
            if (sum == target) {
                return mid;
            }
            if (sum > target) {
                r = mid;
            } else {
                l = mid + 1;
                lastLess = mid;
            }
        }
        return lastLess;
    }
    private int computeBinary(int startIdx, int m) {
        int i = 0, result = 1;
        while (i < m) {
            result = result << 1;
            i++;
        }
        result--;
        i = 0;
        while (i < startIdx) {
            result = result << 1;
            i++;
        }
        return result;
    }
    private int getSum(int[] nums, int binary) {
        int sum = 0, i = 0, n = nums.length;
        while (i < n) {
            if ((binary & 1) == 1) {
                sum += nums[i];
            }
            binary = binary >> 1;
            i++;
        }
        return sum;
    }
    private boolean compare(int big, int small, int m) {
        int i = 0, j = 0, count = 0, bit1 = 1, bit2 = 1;
        while (count < m) {
            while((big & bit1) != bit1) {
                bit1 = bit1 << 1;
                i++;
            }
            while((small & bit2) != bit2) {
                bit2 = bit2 << 1;
                j++;
            }
            if (i < j) {
                return false;
            }
            i++;
            j++;
            bit1 = bit1 << 1;
            bit2 = bit2 << 1;
            count++;
        }
        return true;
    }
    private List<List<Integer>> groupCombination(int start, int end, int m) {
        List<List<Integer>> res = new ArrayList<>();
        int i, j, gn;
        List<Integer> curGroup;
        for (i = start; i <= end; i++) {
            if (Integer.bitCount(i) == m) {
                if (res.isEmpty()) {
                    res.add(new ArrayList<>(List.of(i)));
                } else {
                    gn = res.size();
                    for (j = 0; j < gn; j++) {
                        curGroup = res.get(j);
                        if (compare(i, curGroup.getLast(), m)) {
                            curGroup.add(i);
                            break;
                        }
                    }
                    if (j == gn) {
                        res.add(new ArrayList<>(List.of(i)));
                    }
                }
            }
        }
        return res;
    }
    private int searchGroup(int[] nums, List<Integer> group, int target1, int target2, int total, int min, int bestPossibleMin) {
        int l = 0, r = group.size(), m, cur, curDiff, curMin = min;
        while (l < r) {
            m = (l + r) / 2;
            cur = getSum(nums, group.get(m));
            if (cur == target1 || cur == target2) {
                return bestPossibleMin;
            }
            if (cur < target1) {
                curDiff = total - 2 * cur;
                curMin = Math.min(curMin, curDiff);
                l = m + 1;
            } else if (cur > target2) {
                curDiff = 2 * cur - total;
                curMin = Math.min(curMin, curDiff);
                r = m;
            }
        }
        return curMin;
    }
    public int minimumDifference(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, m = n / 2, i, min;
        int[] presum = new int[n];
        presum[0] = nums[0];
        for (i = 1; i < n; i++) {
            presum[i] = presum[i - 1] + nums[i];
        }
        int target = presum[n - 1] / 2;
        int startIdx = searchStartIdx(presum, target, m);
        int start = computeBinary(startIdx, m), end = computeBinary(m, m);
        min = Math.abs(presum[n - 1] - 2 * getSum(nums, start));
        int bestPossibleMin = presum[n - 1] % 2;
        if (min == bestPossibleMin) {
            return min;
        }
        List<List<Integer>> res = groupCombination(start, end, m);
        for (List<Integer> group : res) {
            for (Integer it : group) {
                System.out.print(it);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
        for (List<Integer> group : res) {
            for (Integer it : group) {
                System.out.print(getSum(nums, it));
                System.out.print(' ');
            }
            System.out.println();
        }
        for (List<Integer> group : res) {
            min = searchGroup(nums, group, target, presum[n - 1] - target, presum[n - 1], min, bestPossibleMin);
            if (min == bestPossibleMin) {
                return min;
            }
        }
        return min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{17, 4, 12, 25, 9, 6, 28, 1, 19, 3};
        int res = s.minimumDifference(nums);
        System.out.println(res);
    }
}
