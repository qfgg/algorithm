import java.util.*;


class Solution {
    boolean isSmall(int[][] envelopes, HashSet<Integer> last, int next) {
        for (Integer l : last) {
            if (envelopes[next][0] > envelopes[l][0] && envelopes[next][1] > envelopes[l][1]) {
                return true;
            }
        }
        return false;
    }
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length, i, j;
        List<Integer> idx1 = new ArrayList<>();
        List<Integer> idx2 = new ArrayList<>();
        int[] pre = new int[n];
        int[] cur = new int[n];
        int[] tmp;
        HashSet<Integer>[] prelast = new HashSet[n];
        HashSet<Integer>[] curlast = new HashSet[n];
        HashSet<Integer>[] tmplast;
        for (i = 0; i < n; i++) {
            idx1.add(i);
            idx2.add(i);
        }
        idx1.sort((a, b) -> envelopes[a][0] == envelopes[b][0] ? envelopes[a][1] - envelopes[b][1] : envelopes[a][0] - envelopes[b][0]);
        idx2.sort((a, b) -> envelopes[a][1] == envelopes[b][1] ? envelopes[a][0] - envelopes[b][0] : envelopes[a][1] - envelopes[b][1]);
        int i1, i2;
        for (i = 0; i < n; i++) {
            i1 = idx1.getFirst();
            i2 = idx2.get(i);
            if (i1 == i2) {
                pre[i] = 1;
                prelast[i] = new HashSet<>(List.of(i1));
            } else if (i > 0) {
                pre[i] = pre[i - 1];
                prelast[i] = prelast[i - 1] == null ? null : new HashSet<>(prelast[i - 1]);
            }
        }
        for (i = 1; i < n; i++) {
            for (j = 0; j < n; j++) {
                i1 = idx1.get(i);
                i2 = idx2.get(j);
                if (i1 == i2 && (j == 0 || prelast[j - 1] == null || isSmall(envelopes, prelast[j - 1], i1))) {
                    cur[j] = j == 0 ? 1 : pre[j - 1] + 1;
                    curlast[j] = new HashSet<>(List.of(i1));
                } else {
                    if (i > 0 && (j == 0 || pre[j] > cur[j - 1])) {
                        cur[j] = pre[j];
                        curlast[j] = prelast[j] == null ? null : new HashSet<>(prelast[j]);
                    } else if (j > 0 && cur[j - 1] > pre[j]) {
                        cur[j] = cur[j - 1];
                        curlast[j] = new HashSet<>(curlast[j - 1]);
                    } else if (pre[j] == cur[j - 1] && pre[j] > 0) {
                        cur[j] = pre[j];
                        curlast[j] = new HashSet<>(prelast[j]);
                        curlast[j].addAll(curlast[j - 1]);
                    }
                }
            }
            tmp = pre;
            pre = cur;
            cur = tmp;
            tmplast = prelast;
            prelast = curlast;
            curlast = tmplast;
        }
        return pre[n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] envelopes = new int[][]{{31,49},{31,45},{37,7},{12,2},{46,18},{44,10},{9,36},{47,44},{32,45},{18,18},{34,7},{23,28},{28,12},{6,33},{21,33},{30,18},{28,31},{43,41},{47,19},{18,50},{12,5},{29,10},{22,13},{17,21},{18,38},{28,46},{41,1},{49,21},{48,5},{40,21},{20,39},{27,13},{15,23},{28,48},{36,44},{18,7},{46,32},{9,41},{22,34},{49,35},{49,34},{22,3},{47,46},{39,25},{29,39},{29,39},{37,11},{41,49},{37,12},{34,1}};
        int res = s.maxEnvelopes(envelopes);
        System.out.println(res);
    }
}
