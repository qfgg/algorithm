import java.util.*;


class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res, nexts;
        if (n == 1) {
            return new ArrayList<>(List.of(0));
        }
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            if (graph.containsKey(edge[0])) {
                graph.get(edge[0]).add(edge[1]);
            } else {
                graph.put(edge[0], new ArrayList<>(List.of(edge[1])));
            }
            if (graph.containsKey(edge[1])) {
                graph.get(edge[1]).add(edge[0]);
            } else {
                graph.put(edge[1], new ArrayList<>(List.of(edge[0])));
            }
        }
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n]; // path starting from n, current node n
        int[][] distRange = new int[n][2];
        int i, maxlen = 0, halflen;
        for (i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                q.add(new int[]{i, i, 0});
                visited[i][i] = true;
                distRange[i][0] = 0;
                distRange[i][1] = 0;
            } else {
                distRange[i][0] = Integer.MIN_VALUE;
                distRange[i][1] = Integer.MIN_VALUE;
            }
        }
        int[] cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            nexts = graph.get(cur[0]);
            for (Integer next: nexts) {
                if (!visited[cur[1]][next]) {
                    visited[cur[1]][next] = true;
                    if (distRange[next][0] == Integer.MIN_VALUE && distRange[next][1] == Integer.MIN_VALUE) {
                        distRange[next][0] = cur[2] + 1;
                        distRange[next][1] = distRange[next][0];
                    } else if (cur[2] + 1 > distRange[next][1]) {
                        distRange[next][1] = cur[2] + 1;
                    } else if (cur[2] + 1 < distRange[next][0]) {
                        distRange[next][0] = cur[2] + 1;
                    }
                    maxlen = Math.max(maxlen, distRange[next][1] - distRange[next][0]);
                    q.add(new int[]{next, cur[1], cur[2] + 1});
                }
            }
        }
        halflen = (maxlen + 1) / 2;
        res = new ArrayList<>();
        for (i = 0; i < n; i++) {
            if (distRange[i][1] == halflen) {
                res.add(i);
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 7;
        int[][] edges = new int[][]{{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}};
        List<Integer> res = s.findMinHeightTrees(n, edges);
        System.out.println(res);
    }
}
