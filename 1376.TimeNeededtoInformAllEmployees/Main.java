import java.util.*;

public class Main {
    public static int dfs(List<List<Integer>> edges, int[] informTime, int id, int time) {
        if (id >= edges.size() || edges.get(id).isEmpty()) {
            return time;
        }
        List<Integer> subs = edges.get(id);
        int max = time;
        for (int sub : subs) {
            max = Math.max(max, dfs(edges, informTime, sub, time + informTime[id]));
        }
        return max;
    }
    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> edges = new ArrayList<>();
        int i, j = 0, cur;
        for (i = 0; i < n; i++) {
            cur = manager[i];
            while (j <= cur) {
                edges.add(new ArrayList<>());
                j++;
            }
            if (cur != -1) {
                edges.get(cur).add(i);
            }
        }
        return dfs(edges, informTime, headID, 0);
    }
    public static void main(String[] args) {
        int n = 6, headID = 2;
        int[] manager = new int[]{2,4,-1,0,2,0};
        int[] informTime = new int[]{3,0,1,0,2,0};
        int res = numOfMinutes(n, headID, manager, informTime);
        System.out.println(res);
    }
}
