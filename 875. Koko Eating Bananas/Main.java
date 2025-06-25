import java.util.*;


class Solution {
    boolean canEat(int[] piles, int h, int speed) {
        int n = piles.length, i, count = 0;
        for (i = 0; i < n; i++) {
            count += piles[i] / speed + (piles[i] % speed == 0 ? 0 : 1);
            if (count > h) {
                return false;
            }
        }
        return true;
    }
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length, i, max = 1, l = 1, r, m, min;
        for (i = 0; i < n; i++) {
            if (piles[i] > max) {
                max = piles[i];
            }
        }
        r = max;
        min = max;
        while (l < r) {
            m = (l + r) / 2;
            if (canEat(piles, h, m)) {
                min = m;
                r = m;
            } else {
                l = m + 1;
            }
        }
        return min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] piles = new int[]{3,6,7,11};
        int h = 8;
        int res = s.minEatingSpeed(piles, h);
        System.out.println(res);
    }
}
