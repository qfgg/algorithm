import java.util.*;


class Solution {
    int getNum(int n, int distanceThreshold, int city, List<int[]>[] graph) {
        int count = 0;
        boolean[] done = new boolean[n];
        int[] dist = new int[n], cur;
        Arrays.fill(dist, Integer.MAX_VALUE);
        done[city] = true;
        dist[city] = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int[] nb : graph[city]) {
            heap.add(nb);
            dist[nb[0]] = nb[1];
        }
        while (!heap.isEmpty()) {
            cur = heap.poll();
            if (cur[1] != dist[cur[0]]) {
                continue;
            }
            done[cur[0]] = true;
            count++;
            for (int[] next : graph[cur[0]]) {
                if (!done[next[0]] && cur[1] + next[1] <= distanceThreshold && cur[1] + next[1] < dist[next[0]]) {
                    dist[next[0]] = cur[1] + next[1];
                    heap.add(new int[]{next[0], dist[next[0]]});
                }
            }
        }
        return count;
    }
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<int[]>[] graph = new List[n];
        int i;
        for (i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            if (edge[2] <= distanceThreshold) {
                graph[edge[0]].add(new int[]{edge[1], edge[2]});
                graph[edge[1]].add(new int[]{edge[0], edge[2]});
            }
        }
        int min = -1, city = -1, next;
        for (i = 0; i < n; i++) {
            next = getNum(n, distanceThreshold, i, graph);
            if (city == -1 || next < min || (next == min && i > city)) {
                min = next;
                city = i;
            }
        }
        return city;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 4, distanceThreshold = 4;
        int[][] edges = new int[][]{{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        int res = s.findTheCity(n, edges, distanceThreshold);
        System.out.println(res);
    }
}
