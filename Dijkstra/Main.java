import java.util.*;


class Main {
    public static void dijkstra(int[][] graph, int start) {
        final int M = Integer.MAX_VALUE;
        int n = graph.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        HashMap<Integer, List<Integer>> path = new HashMap<>();
        path.put(0, new ArrayList<>());
        int[] done = new int[n];
        int[] ret = new int[n];
        int[] dist = new int[n];
        Arrays.fill(dist, M);
        pq.add(new int[]{0, 0});
        while(!pq.isEmpty()) {
            int[] curVertex = pq.poll();
//            same vertext can be added > 1 times because of new short path found
            if (done[curVertex[0]] == 1) {
                continue;
            }
            ret[curVertex[0]] = curVertex[1];
            path.get(curVertex[0]).add(curVertex[0]);
            done[curVertex[0]] = 1;
            for (int i = 0; i < n; i++) {
                if (done[i] == 1||
                        i == curVertex[0] ||
                        graph[curVertex[0]][i] == M) {
                    continue;
                }
                int newDist = curVertex[1] + graph[curVertex[0]][i];
                if (newDist < dist[i]) {
                    dist[i] = newDist;
                    pq.add(new int[]{i, dist[i]});
                    List<Integer> p = path.get(curVertex[0]);
                    List<Integer> newPath = new ArrayList<>(p);
                    path.put(i, newPath);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(i);
            System.out.print(": ");
            System.out.print(ret[i]);
            System.out.print(" ");
            System.out.println(Arrays.toString(path.get(i).toArray()));
        }
    }
    public static void main(String[] args) {
//        0 –> 2 –> 1 的最短路径 5
//        0 –> 2 的最短路径 3
//        0 –> 2 –> 3 的最短路径 6
//        0 –> 2 –> 4 的最短路径 7
//        0 –> 2 –> 3 –> 5 的最短路径 9
        final int M = Integer.MAX_VALUE;
        int[][] graph = new int[][]{
                {0,6,3,M,M,M},
                {6,0,2,5,M,M},
                {3,2,0,3,4,M},
                {M,5,3,0,2,3},
                {M,M,4,2,0,5},
                {M,M,M,3,5,0},
        };
        dijkstra(graph, 0);
    }
}
