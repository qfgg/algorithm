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
    private List<Integer> addElement(List<Integer> comb, int bit) {
        List<Integer> res = new ArrayList<>();
        for (Integer it : comb) {
            res.add(it | bit);
        }
        return res;
    }
    private List<Integer> concatList(List<Integer> l1, List<Integer> l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        l1.addAll(l2);
        return l1;
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer>[][] dp = new List[k + 1][n + 1];
        List<Integer> l1, l2;
        int i, j, m, bit;
        for (i = 1; i <= k; i++) {
            for (j = i; j <= n; j++) {
                bit = 1;
                m = j;
                while (m > 1) {
                    bit = bit << 1;
                    m--;
                }
                l1 = i > 1 && j > 1 ? addElement(dp[i - 1][j - 1], bit) : new ArrayList<>(List.of(bit));
                l2 = j > 1 ? dp[i][j - 1] : null;
                dp[i][j] = concatList(l1, l2);
            }
        }
        int size = dp[k][n].size();
        for (i = 0; i < size; i++) {
            addComb(dp[k][n].get(i), res);
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
