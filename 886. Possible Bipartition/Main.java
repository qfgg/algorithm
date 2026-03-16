import java.util.*;


class Solution {
    void addEdge(int[] link, List<Integer>[] edges) {
        if (edges[link[0] - 1] == null) {
            edges[link[0] - 1] = new ArrayList<>();
        }
        edges[link[0] - 1].add(link[1] - 1);
        if (edges[link[1] - 1] == null) {
            edges[link[1] - 1] = new ArrayList<>();
        }
        edges[link[1] - 1].add(link[0] - 1);
    }
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] edges = new List[n];
        for (int[] link : dislikes) {
            addEdge(link, edges);
        }
        int[] mark = new int[n]; // 0: not visited, 1: group 1, 2: group 2
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (mark[i] > 0) {
                continue;
            }
            mark[i] = 1;
            q.add(i);
            int cur;
            while (!q.isEmpty()) {
                cur = q.poll();
                if (edges[cur] != null) {
                    for (Integer nb : edges[cur]) {
                        if (mark[nb] == mark[cur]) {
                            return false;
                        }
                        if (mark[nb] == 0) {
                            q.add(nb);
                            mark[nb] = mark[cur] ^ 3;
                        }
                    }
                }
            }
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] dislikes = new int[][]{{1,2},{3,4},{4,5},{3,5}};
        boolean res = s.possibleBipartition(5, dislikes);
        System.out.println(res);
    }
}
