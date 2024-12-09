import java.util.*;

public class Main {
    public static int getPow(int n, Map<Integer, Integer> pow) {
        if (pow.containsKey(n)) {
            return pow.get(n);
        }
        int res;
        if (n % 2 == 0) {
            res = getPow(n / 2, pow) + 1;
            pow.put(n, res);
            return res;
        }
        res = getPow(3 * n + 1, pow) + 1;
        pow.put(n, res);
        return res;
    }

    public static int quickSelect(int[][] p, int k, int l, int r) {
        int i = l, j = r;
        int[] pivot = p[l];
        while (i < j) {
            while (i < j) {
                if (p[j][1] < pivot[1] || (p[j][1] == pivot[1] && p[j][0] < pivot[0])) {
                    p[i] = p[j];
                    i++;
                    break;
                }
                j--;
            }
            while (i < j) {
                if (p[i][1] > pivot[1] || (p[i][1] == pivot[1] && p[i][0] > pivot[0])) {
                    p[j] = p[i];
                    j--;
                    break;
                }
                i++;
            }
        }
        p[i] = pivot;
        if (i - l + 1 == k) {
            return p[i][0];
        }
        if (i - l + 1 > k) {
            return quickSelect(p, k, l, i - 1);
        }
        return quickSelect(p, k - 1 - i + l, i + 1, r);
    }
    public static int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> pow = new HashMap<>();
        pow.put(1, 0);
        int[][] p = new int[hi - lo + 1][2];
        for (int i = 0; i <= hi - lo; i++) {
            p[i][0] = lo + i;
            p[i][1] = getPow(lo + i, pow);
        }
        return quickSelect(p, k, 0, hi - lo);
    }
    public static void main(String[] args) {
        int lo = 10, hi = 20, k = 5;
        int res = getKth(lo, hi, k);
        System.out.println(res);
    }
}
