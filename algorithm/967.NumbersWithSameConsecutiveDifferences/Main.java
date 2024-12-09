import java.util.*;


public class Main {
    public static void dfs(int n, int k, int pre, int pos, int result, List<Integer> r) {
        if (pos == n) {
            r.add(result);
            return;
        }
        if (pre + k <= 9) {
            dfs(n, k, pre + k, pos + 1, result * 10 + pre + k, r);
        }
        if (pre - k >= 0) {
            dfs(n, k, pre - k, pos + 1, result * 10 + pre - k, r);
        }
    }
    public static int[] numsSameConsecDiff(int n, int k) {
        int[] ans;
        int i, j;
        if (k == 0) {
            ans = new int[9];
            i = 1;
            while (i <= 9) {
                j = 0;
                while (j < n) {
                    ans[i - 1] = ans[i - 1] * 10 + i;
                    j++;
                }
                i++;
            }
            return ans;
        }
        List<Integer> r = new ArrayList<>();
        for (i = 1; i <= 9; i++) {
            dfs(n, k, i, 1, i, r);
        }
        return r.stream().mapToInt(t->t).toArray();
    }
    public static void main(String[] args) {
        int n = 2, k = 1;
        int[] ans = numsSameConsecDiff(n, k);
        System.out.println(Arrays.toString(ans));
    }
}
