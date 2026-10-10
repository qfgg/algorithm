import java.util.*;


class Solution {
    void computeLeftOrRightBound(int[] nums, int n, boolean isUpperBound, boolean isLeft, int[] ret) {
        Deque<Integer> q = new ArrayDeque<>();
        int i, increment, top;
        if (isLeft) {
            i = n - 1;
            increment = -1;
        } else {
            i = 0;
            increment = 1;
        }
        while ((isLeft && i >= 0) || (!isLeft && i < n)) {
            while (!q.isEmpty() && ((isUpperBound && nums[i] >= nums[q.peek()]) || (!isUpperBound && nums[i] <= nums[q.peek()]))) {
                top = q.pop();
                ret[top] = i;
            }
            q.push(i);
            i += increment;
        }
        while(!q.isEmpty()) {
            top = q.pop();
            ret[top] = top;
        }
    }
    long getAll(long n) {
        return (n + n * n) / 2;
    }
    long getCnt(int[] nums, int i, int[] l, int[] r) {
        int lbound = l[i], rbound = r[i];
        if (lbound < i && nums[lbound] == nums[i]) {
            return 0;
        }
        int j = i, n = nums.length;
        long exclude = lbound == i ? getAll(i) : getAll(i - lbound - 1), whole;
        while (rbound > j && nums[rbound] == nums[j]) {
            exclude += getAll(rbound - j - 1);
            j = rbound;
            rbound = r[rbound];
        }
        if (i == lbound && j == rbound) {
            whole = n;
            exclude += getAll(n - 1 - j);
        } else if (i == lbound) {
            whole = rbound;
            exclude += getAll(rbound - j - 1);
        } else if (j == rbound) {
            whole = n - 1 - lbound;
            exclude += getAll(n - 1 - j);
        } else {
            whole = rbound - lbound - 1;
            exclude += getAll(rbound - j - 1);
        }
        return getAll(whole) - exclude;
    }
    public long subArrayRanges(int[] nums) {
        long maxsum = 0, minsum = 0;
        int i, n = nums.length;
        int[] lbig = new int[n], rbig = new int[n], lsmall = new int[n], rsmall = new int[n];
        computeLeftOrRightBound(nums, n, true, false, rbig);
        computeLeftOrRightBound(nums, n, true, true, lbig);
        computeLeftOrRightBound(nums, n, false, false, rsmall);
        computeLeftOrRightBound(nums, n, false, true, lsmall);
        for (i = 0; i < n; i++) {
            maxsum += (long)nums[i] * getCnt(nums, i, lbig, rbig);
        }
        for (i = 0; i < n; i++) {
            minsum += (long)nums[i] * getCnt(nums, i, lsmall, rsmall);
        }
        return maxsum - minsum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{4,-2,-3,4,1};
        long res = s.subArrayRanges(nums);
        System.out.println(res);
    }
}
