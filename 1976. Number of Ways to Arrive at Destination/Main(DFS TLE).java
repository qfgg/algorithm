import java.util.*;


class Solution {
    int getMinDist(List<int[]>[] graph, int n) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] done = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        heap.add(new int[]{0, 0});
        int[] cur;
        List<int[]> nexts;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            while (cur[1] != dist[cur[0]] && !heap.isEmpty()) {
                cur = heap.poll();
            }
            done[cur[0]] = true;
            dist[cur[0]] = cur[1];
            nexts = graph[cur[0]];
            for (int[] next : nexts) {
                if (!done[next[0]] && cur[1] + next[1] < dist[next[0]]) {
                    dist[next[0]] = cur[1] + next[1];
                    heap.add(new int[]{next[0], dist[next[0]]});
                }
            }
        }
        return dist[n - 1];
    }
    int countWays(List<int[]>[] graph, int source, int target, int dist, boolean[] visited, int mod, HashMap<List<Integer>, Integer> memo) {
        List<Integer> key = new ArrayList<>(List.of(Math.min(source, target), Math.max(source, target), dist));
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (source == target && dist == 0) {
            return 1;
        }
        List<int[]> nexts = graph[source];
        int count = 0;
        for (int[] next : nexts) {
            if (!visited[next[0]] && next[1] <= dist) {
                visited[next[0]] = true;
                count = (count + countWays(graph, next[0], target, dist - next[1], visited, mod, memo)) % mod;
                visited[next[0]] = false;
            }
        }
        memo.put(new ArrayList<>(List.of(Math.min(source, target), Math.max(source, target), dist)), count);
        return count;
    }
    public int countPaths(int n, int[][] roads) {
        List<int[]>[] graph = new List[n];
        int i;
        for (i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            graph[road[0]].add(new int[]{road[1], road[2]});
            graph[road[1]].add(new int[]{road[0], road[2]});
        }
        int minDist = getMinDist(graph, n);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int mod = 1000000007;
        HashMap<List<Integer>, Integer> memo = new HashMap<>();
        int cnt = countWays(graph, 0, n - 1, minDist, visited, mod, memo);
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 7;
        int[][] roads = new int[][]{
                {0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}
        };
        int res = s.countPaths(n, roads);
        System.out.println(res);
    }
}
