import java.util.*;

public class Main {
    public static int getRangeState(int[] a, int[] b) {
        double d = (double)(a[0] - b[0]) / 100000 * (a[0] - b[0]) + (double)(a[1] - b[1]) / 100000 * (a[1] - b[1]);
        double r1 = (double)a[2] / 100000 * a[2], r2 = (double)b[2] / 100000 * b[2];
        if (d <= r1 && d <= r2) {
            return 3;
        }
        if (d <= r1) {
            return 1;
        }
        if (d <= r2) {
            return 2;
        }
        return 0;
    }

    public static int dfs(int start, List<Integer>[] e, boolean[] seen, int[] memo) {
        if (memo[start] > 0) {
            return memo[start];
        }
        seen[start] = true;
        List<Integer> edges = e[start];
        int i, n = edges.size(), cnt = 1;
        for (i = 0; i < n; i++) {
            if (!seen[edges.get(i)]) {
                cnt += dfs(edges.get(i), e, seen, memo);
            }
        }
        memo[start] = cnt;
        return cnt;
    }
    public static int maximumDetonation(int[][] bombs) {
        int n = bombs.length, i, j, relation;
        List<Integer>[] e = new List[n];
        for (i = 0; i < n; i++) {
            e[i] = new ArrayList<>();
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < i; j++) {
                relation = getRangeState(bombs[j], bombs[i]);
                if (relation == 3) {
                    e[j].add(i);
                    e[i].add(j);
                } else if (relation == 1) {
                    e[j].add(i);
                } else if (relation == 2) {
                    e[i].add(j);
                }
            }
        }
        int max = 1;
        int[] memo = new int[n];
        boolean[] seen = new boolean[n];
        for (i = 0; i < n; i++) {
            max = Math.max(max, dfs(i, e, seen, memo));
            Arrays.fill(memo, 0);
            Arrays.fill(seen, false);
        }
        return max;
    }
    public static void main(String[] args) {
        int[][] bombs = new int[][]{
                {855,82,158},{17,719,430},{90,756,164},
                {376,17,340},{691,636,152},{565,776,5},
                {464,154,271},{53,361,162},{278,609,82},
                {202,927,219},{542,865,377},{330,402,270},
                {720,199,10},{986,697,443},{471,296,69},
                {393,81,404},{127,405,177}
        };
        int res = maximumDetonation(bombs);
        System.out.println(res);
    }
}
