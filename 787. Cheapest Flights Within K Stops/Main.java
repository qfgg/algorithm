import java.util.*;


class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> edges = new HashMap<>();
        Map<Integer, Integer> nb;
        for (int[] flight : flights) {
            if (edges.containsKey(flight[0])) {
                nb = edges.get(flight[0]);
            } else {
                nb = new HashMap<>();
                edges.put(flight[0], nb);
            }
            nb.put(flight[1], flight[2]);
        }
        int[] cur;
        int[][] cost = new int[n][k + 3];
        int[] lastPointCnt = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        Arrays.fill(lastPointCnt, -1);
        cost[src][1] = 0;
        lastPointCnt[src] = 1;
        int min = Integer.MAX_VALUE, nextCost;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0, 1});
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur[0] == dst && cur[2] <= k + 2) {
                min = Math.min(min, cur[1]);
            }
            if (edges.containsKey(cur[0])) {
                nb = edges.get(cur[0]);
                for (Integer next: nb.keySet()) {
                    nextCost = cur[1] + nb.get(next);
                    if (cur[2] <= k + 1 &&
                            (lastPointCnt[next] == -1 || nextCost < cost[next][lastPointCnt[next]]) &&
                            nextCost < cost[next][cur[2] + 1]) {
                        cost[next][cur[2] + 1] = nextCost;
                        lastPointCnt[next] = cur[2] + 1;
                        queue.add(new int[]{next, nextCost, cur[2] + 1});
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 5, src = 0, dst = 2, k = 2;
        int[][] flights = new int[][]{{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}};
        int res = s.findCheapestPrice(n, flights, src, dst, k);
        System.out.println(res);
    }
}
