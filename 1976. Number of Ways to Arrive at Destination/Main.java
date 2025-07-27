import java.util.*;


class Solution {
    int dijkstraAndCount(List<int[]>[] graph, int n) {
        int mod = 1000000007;
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        boolean[] done = new boolean[n];
        long[] dist = new long[n];
        int[] cnt = new int[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        heap.add(new long[]{0, 0});
        cnt[0] = 1;
        long[] cur;
        List<int[]> nexts;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            while (cur[1] != dist[(int)cur[0]] && !heap.isEmpty()) {
                cur = heap.poll();
            }
            done[(int)cur[0]] = true;
            dist[(int)cur[0]] = cur[1];
            nexts = graph[(int)cur[0]];
            for (int[] next : nexts) {
                if (cur[1] + next[1] == dist[next[0]]) {
                    cnt[next[0]] = (cnt[next[0]] + cnt[(int)cur[0]]) % mod;
                }
                if (!done[next[0]] && cur[1] + next[1] < dist[next[0]]) {
                    cnt[next[0]] = cnt[(int)cur[0]];
                    dist[next[0]] = cur[1] + next[1];
                    heap.add(new long[]{next[0], dist[next[0]]});
                }
            }
        }
        return cnt[n - 1];
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
        int cnt = dijkstraAndCount(graph, n);
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 4;
        int[][] roads = new int[][]{
                {0,1,1000000000},
                {1,2,1000000000},
                {2,3,1000000000},
        };
        System.out.println(s.countPaths(n, roads));
    }
}
