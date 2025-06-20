import java.util.*;


class Solution {
    boolean dfs(List<Integer>[] edges, int cur, boolean[] visited, int[] group) {
        List<Integer> nexts = edges[cur];
        for (Integer next : nexts) {
            if (!visited[next]) {
                group[next] = group[cur] ^ 3;
                visited[next] = true;
                if (!dfs(edges, next, visited, group)) {
                    return false;
                }
            } else if (group[next] > 0 && group[next] == group[cur]) {
                return false;
            }
        }
        return true;
    }
    public boolean isBipartite(int[][] graph) {
        int n = graph.length, i;
        List<Integer>[] edges = new List[n];
        for (i = 0; i < n; i++) {
            edges[i] = new ArrayList();
        }
        for (i = 0; i < n; i++) {
            for (int j : graph[i]) {
                edges[i].add(j);
            }
        }

        boolean[] visited = new boolean[n];
        int[] group = new int[n];
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                group[i] = 1;
                if(!dfs(edges, i, visited, group)) {
                    return false;
                }
                visited[i] = true;
            }
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] graph = new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}};
        boolean res = s.isBipartite(graph);
        System.out.println(res);
    }
}
