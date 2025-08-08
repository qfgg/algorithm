import java.util.*;


class Solution {
    public long minEnd(int n, int x) {
        if (n == 1) {
            return x;
        }
        long res = x, extraBits = 0;
        int extra = n - 1, source = x, move = 0;
        while (extra > 0) {
            while ((source & 1) == 1) {
                source = source >> 1;
                move++;
            }
            extraBits = extraBits | ((long)(extra & 1) << move);
            extra = extra >> 1;
            source = source >> 1;
            move++;
        }
        res = res | extraBits;
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 3, x = 4;
        long res = s.minEnd(n, x);
        System.out.println(res);
    }
}
