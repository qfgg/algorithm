import java.util.*;


class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int i = 0, n = s.length(), sum = 0, index = 0, qn = queries.length;
        int[] l = new int[n], r = new int[n], ret = new int[qn];
        for (int[] query : queries) {
            l[query[0]] = 1;
            r[query[1]] = 1;
        }
        List<Integer> counts = new ArrayList<>();
        char ch = s.charAt(i);
        while (ch != '|') {
            if (l[i] == 1) {
                l[i] = 0;
            }
            if (r[i] == 1) {
                r[i] = -1;
            }
            i++;
            if (i == n) {
                break;
            }
            ch = s.charAt(i);
        }
        for (; i < n; i++) {
            ch = s.charAt(i);
            if (ch == '*') {
                sum++;
                if (l[i] == 1) {
                    l[i] = index + 1;
                }
                if (r[i] == 1) {
                    r[i] = index - 1;
                }
            } else if (ch == '|') {
                if (sum > 0) {
                    counts.add(sum);
                    sum = 0;
                    index++;
                }
                if (l[i] == 1) {
                    l[i] = index;
                }
                if (r[i] == 1) {
                    r[i] = index - 1;
                }
            }
        }
        n = counts.size();
        int[] presum = new int[n];
        if (n == 0) {
            return ret;
        }
        presum[0] = counts.getFirst();
        for (i = 1 ; i < n; i++) {
            presum[i] = presum[i - 1] + counts.get(i);
        }
        for (i = 0; i < qn; i++) {
            if (r[queries[i][1]] >= l[queries[i][0]]) {
                ret[i] = l[queries[i][0]] > 0 ? presum[r[queries[i][1]]] - presum[l[queries[i][0]] - 1] : presum[r[queries[i][1]]];
            }
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "***";
        int[][] queries = new int[][]{{2,2}};
        int[] res = s.platesBetweenCandles(str, queries);
        System.out.println(Arrays.toString(res));
    }
}
