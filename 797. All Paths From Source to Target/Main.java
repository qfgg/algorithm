import java.util.*;


class Solution {
    void dfs(int start, int end, int[][] graph, List<Integer> path, List<List<Integer>> ret) {
        if (start == end) {
            ret.add(new ArrayList<>(path));
            return;
        }
        int i, len = graph[start].length;
        for (i = 0 ; i < len; i++) {
            path.add(graph[start][i]);
            dfs(graph[start][i], end, graph, path, ret);
            path.removeLast();
        }
    }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, graph.length - 1, graph, path, ret);
        path.removeLast();
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] graph = new int[][]{
                {4,3,1},
                {3,2,4},
                {3},
                {4},
                {}
        };
        List<List<Integer>> res = s.allPathsSourceTarget(graph);
        System.out.println(res);
    }
}
