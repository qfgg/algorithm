import java.util.*;


class Solution {
    private int countMisses(int[] src, int[] backup, int target) {
        int count = 0, i, n = src.length;
        for (i = 0; i < n; i++) {
            if (src[i] != target) {
                if (backup[i] == target) {
                    count++;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return count;
    }
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int min;
        if (tops[0] == bottoms[0]) {
             min = Math.min(countMisses(tops, bottoms, tops[0]), countMisses(bottoms, tops, tops[0]));
        } else {
            min = Math.min(Math.min(countMisses(tops, bottoms, tops[0]), countMisses(tops, bottoms, bottoms[0])),
                    Math.min(countMisses(bottoms, tops, tops[0]), countMisses(bottoms, tops, bottoms[0])));
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] tops = new int[]{2,1,2,4,2,2};
        int[] bottoms = new int[]{5,2,6,2,3,2};
        int res = s.minDominoRotations(tops, bottoms);
        System.out.println(res);
    }
}
