import java.util.*;


class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] locations = new int[1001];
        for (int[] trip : trips) {
            locations[trip[1]] += trip[0];
            locations[trip[2]] -= trip[0];
        }
        int i, cur = 0;
        for (i = 0; i <= 1000; i++) {
            cur += locations[i];
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int[][] trips = new int[][]{{2, 1, 5}, {3, 3, 7}};
        int capacity = 4;
        boolean ret = slt.carPooling(trips, capacity);
        System.out.println(ret);
    }
}
