import java.util.*;


class Solution {
    List<List<Integer>> dfs(HashMap<Integer, List<Integer>> graph, int cur, boolean[] visited, int[] longest, List<List<Integer>>[] longestPath) {
        List<List<Integer>> curLong;
        List<Integer> nexts = graph.get(cur);
        int len, totalLen;
        List<Integer> l1 = null, l2 = null, tmp, newL1, newL2;
        for (Integer next: nexts) {
            if (!visited[next]) {
                visited[next] = true;
                curLong = dfs(graph, next, visited, longest, longestPath);
                len = curLong.size() == 1 ? curLong.getFirst().size() : Math.max(curLong.getFirst().size(), curLong.get(1).size());
                tmp = curLong.size() == 1 || curLong.getFirst().size() >= curLong.get(1).size()? curLong.getFirst() : curLong.get(1);
                if (l1 == null) {
                    l1 = tmp;
                } else if (l2 == null) {
                    if (len >= l1.size()) {
                        l2 = tmp;
                    } else {
                        l2 = l1;
                        l1 = tmp;
                    }
                } else if (len > l2.size()) {
                    l1 = l2;
                    l2 = tmp;
                } else if (len > l1.size()) {
                    l1 = tmp;
                }
            }
        }
        if (l1 == null) {
            curLong = new ArrayList<>();
            curLong.add(new ArrayList<>(List.of(cur)));
            if (longest[1] == 0) {
                longest[0] = cur;
                longest[1] = 1;
                longestPath[0] = curLong;
            }
            return curLong;
        }
        List<List<Integer>> l = new ArrayList<>();
        newL1 = new ArrayList<>(l1);
        newL1.add(cur);
        l.add(newL1);
        if (l2 != null) {
            newL2 = new ArrayList<>(l2);
            newL2.add(cur);
            l.add(newL2);
        }
        totalLen = l1.size() + 1 + (l2 == null ? 0 : l2.size());
        if (totalLen > longest[1]) {
            longest[0] = cur;
            longest[1] = totalLen;
            longestPath[0] = l;
        }
        return l;
    }
    void getRoot(List<List<Integer>> longs, List<Integer> res) {
        List<Integer> l = new ArrayList<>();
        List<Integer> l1 = longs.getFirst(), l2 = longs.size() > 1 ? longs.get(1) : null;
        l.addAll(l1);
        if (l2 != null) {
            int max = l2.size() - 2, i;
            for (i = max; i >= 0; i--) {
                l.add(l2.get(i));
            }
        }
        int i = 0, j = l.size() - 1;
        while (i < j) {
            if (j == i + 1) {
                res.add(l.get(i));
                res.add(l.get(j));
            } else if (j == i + 2) {
                res.add(l.get(i + 1));
            }
            i++;
            j--;
        }
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return new ArrayList<>(List.of(0));
        }
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            if (graph.containsKey(edge[0])) {
                graph.get(edge[0]).add(edge[1]);
            } else {
                graph.put(edge[0], new ArrayList<>(List.of(edge[1])));
            }
            if (graph.containsKey(edge[1])) {
                graph.get(edge[1]).add(edge[0]);
            } else {
                graph.put(edge[1], new ArrayList<>(List.of(edge[0])));
            }
        }
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[n];
        List<List<Integer>>[] longestPath = new List[1];
        int[] longest = new int[2];
        visited[0] = true;
        dfs(graph, 0, visited, longest, longestPath);
        getRoot(longestPath[0], res);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 2;
        int[][] edges = new int[][]{{0,1}};
        List<Integer> res = s.findMinHeightTrees(n, edges);
        System.out.println(res);
    }
}
