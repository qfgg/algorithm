import java.util.*;

class UF {
    int[] parent;
    int count;
    UF(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        count = n;
    }
    int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        parent[n] = find(parent[n]);
        return parent[n];
    }

    void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot != bRoot) {
            parent[aRoot] = bRoot;
            count--;
        }
    }
}
public class Main {
    public static int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        UF uf = new UF(n);
        for (int[] log : logs) {
            uf.union(log[1], log[2]);
            if (uf.count == 1) {
                return log[0];
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] logs = new int[][]{
                {20190101,0,1},
                {20190104,3,4},
                {20190107,2,3},
                {20190211,1,5},
                {20190224,2,4},
                {20190301,0,3},
                {20190312,1,2},
                {20190322,4,5}
        };
        int N = 6;
        int ans = earliestAcq(logs, N);
        System.out.println(ans);
    }
}
