import java.util.*;


class Solution {
    void insertAHeightList(List<Integer> inc, List<Integer> hl) {
        List<Integer> copy = new ArrayList<>(inc);
        int len, i, start = 0;
        for (Integer h : hl) {
            len = inc.size();
            for (i = start; i < len; i++) {
                if ((i == 0 || h > copy.get(i - 1)) && h <= copy.get(i)) {
                    inc.set(i, h);
                    start = i + 1;
                    break;
                }
            }
            if (i == len && (len == 0 || h > copy.get(len - 1))) {
                inc.add(h);
                break;
            }
        }
    }
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length, i;
        List<Integer> idx = new ArrayList<>();
        for (i = 0; i < n; i++) {
            idx.add(i);
        }
        idx.sort((a, b) -> envelopes[a][0] == envelopes[b][0] ? envelopes[a][1] - envelopes[b][1] : envelopes[a][0] - envelopes[b][0]);
        List<List<Integer>> heights = new ArrayList<>();
        List<Integer> curHeight;
        int prew = -1;
        int[] cur;
        for (i = 0; i < n; i++) {
            cur = envelopes[idx.get(i)];
            if (cur[0] != prew) {
                heights.add(new ArrayList<>(List.of(cur[1])));
                prew = cur[0];
            } else {
                curHeight = heights.getLast();
                if (curHeight.getLast() < cur[1]) {
                    curHeight.add(cur[1]);
                }
            }
        }
        List<Integer> inc = new ArrayList<>();
        for (List<Integer> hl : heights) {
            insertAHeightList(inc, hl);
        }
        return inc.size();
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
