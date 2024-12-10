import java.util.*;

class Solution {
    Random rd = new Random();
    int[] range;

    public Solution(int[] w) {
        range = new int[w.length];
        int l = range.length;
        range[0] = w[0];
        for (int i = 1; i < l; i++) {
            range[i] = range[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int pick = rd.nextInt(range[range.length - 1]);
        int l = 0, r = range.length - 1, m;
        while (l < r) {
            m = (l + r) / 2;
            if (range[m] > pick) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
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
