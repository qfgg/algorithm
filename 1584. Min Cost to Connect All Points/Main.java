import java.util.*;


class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        if (n <= 1) {
            return 0;
        }
        int i, cur, totalCost = 0, len, qIdx, cnt = 1;
        boolean[] done = new boolean[n];
        PriorityQueue<Integer>[] q = new PriorityQueue[n];
        PriorityQueue<int[]> mins = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Map<Integer, Queue<Integer>>[] len2points = new Map[n];
        Queue<Integer> nb;
        List<Integer> lens = null;
        int[] next;
        for (i = 0; i < n; i++) {
            len2points[i] = new HashMap<>();
        }
        cur = 0;
        while (cnt < n) {
            done[cur] = true;
            lens = new ArrayList<>();
            for (i = 0; i < n; i++) {
                if (i != cur && !done[i]) {
                    len = Math.abs(points[cur][0] - points[i][0]) + Math.abs(points[cur][1] - points[i][1]);
                    lens.add(len);
                    if (len2points[cur].containsKey(len)) {
                        nb = len2points[cur].get(len);
                    } else {
                        nb = new LinkedList<>();
                        len2points[cur].put(len, nb);
                    }
                    nb.add(i);
                }
            }
            q[cur] = new PriorityQueue<>(lens);
            mins.add(new int[]{cur, q[cur].peek()});
            while (true) {
                next = mins.poll();
                qIdx = next[0];
                q[qIdx].poll();
                if (!q[qIdx].isEmpty()) {
                    mins.add(new int[]{qIdx, q[qIdx].peek()});
                }
                nb = len2points[qIdx].get(next[1]);
                cur = nb.poll();
                if (!done[cur]) {
                    totalCost += next[1];
                    break;
                }
            }
            cnt++;
        }
        return totalCost;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] points = new int[][]{{0,0},{7,0},{3,10},{5,2},{2,2}};
        int res = s.minCostConnectPoints(points);
        System.out.println(res);
    }
}
