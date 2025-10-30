import java.util.*;


class Solution {
    Map<Integer, Set<Integer>> getEdgeMap(int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            Set<Integer> nb;
            if (map.containsKey(edge[0])) {
                nb = map.get(edge[0]);
            } else {
                nb = new HashSet<>();
                map.put(edge[0], nb);
            }
            nb.add(edge[1]);
        }
        return map;
    }
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] res = new int[n];
        int[][] min = new int[2][n];
        Arrays.fill(min[0], Integer.MAX_VALUE);
        Arrays.fill(min[1], Integer.MAX_VALUE);
        Map[] edgeMaps = new Map[2];
        edgeMaps[0] = getEdgeMap(redEdges);
        edgeMaps[1] = getEdgeMap(blueEdges);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0}); // id, distance, color
        queue.add(new int[]{0, 0, 1});
        boolean[][] done = new boolean[2][n];
        done[0][0] = true;
        done[1][0] = true;
        int[] cur;
        Set<Integer> nb;
        Map<Integer, Set<Integer>> map;
        int otherColor;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            min[cur[2]][cur[0]] = cur[1];
            otherColor = cur[2] ^ 1;
            map = edgeMaps[otherColor];
            if (map.containsKey(cur[0])) {
                nb = map.get(cur[0]);
                for (Integer next : nb) {
                    if (!done[otherColor][next]) {
                        done[otherColor][next] = true;
                        queue.add(new int[]{next, cur[1] + 1, otherColor});
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            res[i] = Math.min(min[0][i], min[1][i]);
            if (res[i] == Integer.MAX_VALUE) {
                res[i] = -1;
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 4;
        int[][] redEdges = new int[][]{{1,2},{0,1},{2,3}};
        int[][] blueEdges = new int[][]{{1,1},{1,2}};
        int[] res = s.shortestAlternatingPaths(n, redEdges, blueEdges);
        System.out.println(Arrays.toString(res));
    }
}
