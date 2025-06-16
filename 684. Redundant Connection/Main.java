import java.util.*;


class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length, i, cur, size, nb;
        List<Integer>[] graph = new List[n + 1];
        int[] deg = new int[n + 1];
        boolean[] vertex = new boolean[n + 1];
        Arrays.fill(vertex, true);
        for (i = 0; i < n; i++) {
            if (graph[edges[i][0]] == null) {
                graph[edges[i][0]] = new ArrayList<>();
            }
            deg[edges[i][0]]++;
            graph[edges[i][0]].add(edges[i][1]);
            if (graph[edges[i][1]] == null) {
                graph[edges[i][1]] = new ArrayList<>();
            }
            deg[edges[i][1]]++;
            graph[edges[i][1]].add(edges[i][0]);
        }
        Queue<Integer> q = new LinkedList<>();
        for (i = 1; i <= n; i++) {
            if (deg[i] == 1) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            cur = q.poll();
            deg[cur]--;
            vertex[cur] = false;
            size = graph[cur].size();
            for(i = 0; i < size; i++) {
                nb = graph[cur].get(i);
                deg[nb]--;
                if(deg[nb] == 1 ) {
                    q.add(nb);
                }
            }
        }
        for (i = n - 1; i >= 0; i--) {
            if (vertex[edges[i][0]] && vertex[edges[i][1]]) {
                break;
            }
        }
        return edges[i];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] edges = new int[][]{{1,2},{1,3},{2,3}};
        int[] res = s.findRedundantConnection(edges);
        System.out.println(Arrays.toString(res));
    }
}
