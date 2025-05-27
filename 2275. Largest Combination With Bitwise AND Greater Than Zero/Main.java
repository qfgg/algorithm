import java.util.*;


class Solution {
    public int largestCombination(int[] candidates) {
        int n = candidates.length, i, j, maxnum = candidates[0], bn = 1, maxsize = 1;
        for (i = 1; i < n; i++) {
            maxnum = Math.max(maxnum, candidates[i]);
        }
        i = 1;
        while (i < maxnum) {
            i = i << 1;
            bn++;
        }
        int[] count = new int[bn];
        int[] bits = new int[bn];
        bits[0] = 1;
        for (i = 1; i < bn; i++) {
            bits[i] = bits[i - 1] << 1;
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < bn; j++) {
                if ((candidates[i] & bits[j]) > 0) {
                    count[j]++;
                    maxsize = Math.max(maxsize, count[j]);
                }
            }
        }
        return maxsize;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] candidates = new int[]{16,17,71,62,12,24,14};
        int res = s.largestCombination(candidates);
        System.out.println(res);
    }
}
