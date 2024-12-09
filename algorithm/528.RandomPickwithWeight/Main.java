import java.util.*;

class Solution {
    int[] range;
    int max;

    public Solution(int[] w) {
        range = new int[w.length + 1];
        int l = range.length;
        for (int i = 1; i < l; i++) {
            range[i] = range[i - 1] + w[i - 1];
        }
        max = range[l - 1];
    }

    public int pickIndex() {
        if (range.length == 2) {
            return 0;
        }
        Random rd = new Random();
        int pick = rd.nextInt(max);
        int l = 0, r = range.length - 1, m;
        while (l <= r) {
            m = (l + r) / 2;
            if (range[m] == pick) {
                return m;
            }
            if (range[m] > pick) {
                r = m - 1;
                if (r < l) {
                    return m - 1;
                }
            } else {
                l = m + 1;
                if (l > r) {
                    return m;
                }
            }
        }
        return -1;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1, 3});
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
    }
}
