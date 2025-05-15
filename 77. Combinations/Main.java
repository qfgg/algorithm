import java.util.*;


class Solution {
    private void addComb(int bit, List<List<Integer>> res) {
        List<Integer> comb = new ArrayList<>();
        int b = bit, i = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                comb.add(i);
            }
            b = b >> 1;
            i++;
        }
        res.add(comb);
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int b = 1, i = 0;
        while (i < n) {
            b = b << 1;
            i++;
        }
        b--;
        i = 1;
        while (i <= b) {
            if (Integer.bitCount(i) == k) {
                addComb(i, res);
            }
            i++;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 4, k = 2;
        List<List<Integer>> res = s.combine(n, k);
        System.out.println(res);
    }
}
