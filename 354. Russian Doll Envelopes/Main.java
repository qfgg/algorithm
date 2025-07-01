import java.util.*;


class Solution {
    int binarySearch(List<Integer> list, int start, int end, int h) {
        int l = start, r = end + 1, m, cur, larger = -1;
        while (l < r) {
            m = (l + r) / 2;
            cur = list.get(m);
            if (cur == h) {
                return m;
            }
            if (cur > h) {
                larger = m;
                r = m;
            } else {
                l = m + 1;
            }
        }
        return larger;
    }
    void insertAHeightList(List<Integer> inc, List<Integer> hl) {
        List<Integer> copy = new ArrayList<>(inc);
        int start = 0, end = copy.size() - 1, idx;
        for (Integer h : hl) {
            idx = binarySearch(copy, start, end, h);
            if (idx == -1 && (copy.isEmpty() || h > copy.getLast())) {
                inc.add(h);
                break;
            }
            if ((idx == 0 || (idx > 0 && h > copy.get(idx - 1)) && h <= copy.get(idx))) {
                inc.set(idx, h);
                start = idx + 1;
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
        int[][] envelopes = new int[][]{{5,4},{6,4},{6,7},{2,3}};
        int res = s.maxEnvelopes(envelopes);
        System.out.println(res);
    }
}
