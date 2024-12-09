import java.util.*;


public class Main {
    public static void buildGraph(int[][] stones, Map<Integer, List<Integer>> graph, Map<Integer, Integer> deg) {
        List<Integer> edges;
        Arrays.sort(stones, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int[] preStone = null;
        int pre = -1, cur;
        for (int[] stone : stones) {
            cur = stone[0] * 10001 + stone[1];
            if (preStone != null && preStone[1] == stone[1]) {
                graph.putIfAbsent(pre, new ArrayList<>());
                edges = graph.get(pre);
                edges.add(cur);
                graph.putIfAbsent(cur, new ArrayList<>());
                edges = graph.get(cur);
                edges.add(pre);
                deg.merge(pre, 1, Integer::sum);
                deg.merge(cur, 1, Integer::sum);
            }
            preStone = stone;
            pre = cur;
        }
        Arrays.sort(stones, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        preStone = null;
        for (int[] stone : stones) {
            cur = stone[0] * 10001 + stone[1];
            if (preStone != null && preStone[0] == stone[0]) {
                graph.putIfAbsent(pre, new ArrayList<>());
                edges = graph.get(pre);
                edges.add(cur);
                graph.putIfAbsent(cur, new ArrayList<>());
                edges = graph.get(cur);
                edges.add(pre);
                deg.merge(pre, 1, Integer::sum);
                deg.merge(cur, 1, Integer::sum);
            }
            preStone = stone;
            pre = cur;
        }
    }
    public static int removeStones(int[][] stones) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> deg = new HashMap<>();
        buildGraph(stones, graph, deg);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (Integer k : deg.keySet()) {
            pq.add(new int[]{k, deg.get(k)});
        }
        int cnt = 0, nDeg, vn;
        int[] cur;
        List<Integer> e;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (deg.get(cur[0]) != cur[1]) {
                continue;
            }
            if (deg.get(cur[0]) > 0) { // cannot delete point connecting > 1 points with bigger deg
                e = graph.get(cur[0]);
                vn = 0;
                for (Integer vk : e) {
                    if (deg.get(vk) > deg.get(cur[0])) {
                        vn++;
                        if (vn > 1) {
                            break;
                        }
                    }
                }
                if (vn > 1) {
                    continue;
                }
                deg.put(cur[0], 0);
                cnt++;
                for (Integer vk : e) {
                    nDeg = deg.get(vk);
                    if (nDeg > 0) {
                        deg.put(vk, nDeg - 1);
                        pq.add(new int[]{vk, nDeg - 1});
                    }
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[][] stones = new int[][]{
                {0, 0}, {0, 3}, {2, 0}, {2, 3}, {2, 4},
                {4, 0}, {4, 5}, {4, 8}, {5, 5}, {5, 8}
        };
        int res = removeStones(stones);
        System.out.println(res);
    }
}
