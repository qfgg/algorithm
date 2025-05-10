import java.util.*;


class Solution {
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
    private List<Integer>[] computeHalfSums(int[] nums, int m, int start, int end, int halfTotal, HashSet<Integer>[] set) {
        int i, n1, sum;
        for (i = start; i < end; i++) {
            n1 = Integer.bitCount(i);
            if (n1 <= m / 2) {
                sum = getSum(nums, i);
                set[n1 - 1].add(sum);
                if (m - n1 != n1) {
                    set[m - n1 - 1].add(halfTotal - sum);
                }
            }
        }
        List<Integer>[] sortedListArray = new List[m - 1];
        List<Integer> sortedList;
        for (i = 0; i < m - 1; i++) {
            sortedList = new ArrayList<>(set[i]);
            Collections.sort(sortedList);
            sortedListArray[i] = sortedList;
        }
        return sortedListArray;
    }
    private int searchOne(int lsum, List<Integer> rlist, int ltarget, int rtarget, int total, int initalMin) {
        int l = 0, r = rlist.size(), m, curSum, min = initalMin;
        while (l < r) {
            m = (l + r) / 2;
            curSum = lsum + rlist.get(m);
            if (curSum == ltarget || curSum == rtarget) {
                return ltarget == rtarget ? 0 : 1;
            }
            if (curSum < ltarget) {
                min = Math.min(min, total - 2 * curSum);
                l = m + 1;
            } else if (curSum > rtarget) {
                min = Math.min(min, 2 * curSum - total);
                r = m;
            }
        }
        return min;
    }
    private int searchMin(List<Integer>[] llist, List<Integer>[] rlist, int m, int ltarget, int rtarget, int total, int initalMin) {
        int min = initalMin, n1, i, j;
        for (i = m - 2; i >= 0; i--) {
            n1 = llist[i].size();
            for (j = n1 - 1; j >= 0; j--) {
                min = searchOne(llist[i].get(j), rlist[m - 2 - i], ltarget, rtarget, total, min);
            }
        }
        return min;
    }
    public int minimumDifference(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, m = n / 2, i, min;
        int[] presum = new int[n];
        presum[0] = nums[0];
        for (i = 1; i < n; i++) {
            presum[i] = presum[i - 1] + nums[i];
        }
        int total = presum[n - 1], target = total / 2;
        int bestPossibleMin = total % 2;
        if (presum[m - 1] == target) {
            return bestPossibleMin;
        }
        HashSet<Integer>[] lset = new HashSet[m - 1];
        HashSet<Integer>[] rset = new HashSet[m - 1];
        for (i = 0; i < m - 1; i++) {
            lset[i] = new HashSet<>();
            rset[i] = new HashSet<>();
        }
        int end1 = 1;
        i = 0;
        while (i < m) {
            end1 = end1 << 1;
            i++;
        }
        List<Integer>[] llist = computeHalfSums(nums, m,1, end1, presum[m - 1], lset);
        int[] rightHalf = Arrays.copyOfRange(nums, m, n);
        List<Integer>[] rlist = computeHalfSums(rightHalf, m, 1, end1,total - presum[m - 1], rset);
        min = total - 2 * presum[m - 1];
        return searchMin(llist, rlist, m, target, total - target, total, min);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{
                2,-1,0,4,-2,-9
        };
        int res = s.minimumDifference(nums);
        System.out.println(res);
    }
}
