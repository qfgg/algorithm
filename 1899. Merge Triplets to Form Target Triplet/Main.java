import java.util.*;


class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int mask = 0, k, bit, curMask;
        for (int[] triplet : triplets) {
            bit = 1;
            curMask = 0;
            for (k = 0; k < 3; k++) {
                if (triplet[k] > target[k]) {
                    break;
                }
                if (triplet[k] == target[k]) {
                    curMask |= bit;
                }
                bit = bit << 1;
            }
            if (k == 3) {
                mask |= curMask;
                if (mask == 7) {
                    return true;
                }
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] triplets = new int[][]{{2,5,3},{2,3,4},{1,2,5},{5,2,3}};
        int[] target = new int[]{5,5,5};
        boolean res = s.mergeTriplets(triplets, target);
        System.out.println(res);
    }
}
