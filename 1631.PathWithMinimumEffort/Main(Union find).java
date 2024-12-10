import java.util.*;


class UF {
    int[] parents;
    int[] sizes;
    UF(int size) {
        parents = new int[size];
        sizes = new int[size];
        for (int i = 0; i < size; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }
    int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        int res = find(parents[a]);
        parents[a] = res;
        return res;
    }
    void union(int a, int b) {
        int ap = find(a);
        int bp = find(b);
        if (ap != bp) {
            int l = ap, s = bp;
            if (sizes[a] < sizes[b]) {
                l = bp;
                s = ap;
            }
            parents[s] = l;
            sizes[l] += sizes[s];
        }
    }
    boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }
}
public class Main {
    public static int minimumEffortPath(int[][] heights) {
        int rn = heights.length, cn = heights[0].length, en = 2 * rn * cn - cn - rn;
        int[][] edges = new int[en][3];
        int k = 0;
        for (int i = 0; i < rn; i++) {
            for (int j = 0; j < cn; j++) {
                if (i > 0) {
                    edges[k] = new int[]{
                            (i - 1) * cn + j,
                            i * cn + j,
                            Math.abs(heights[i - 1][j] - heights[i][j])
                    };
                    k++;
                }
                if (j > 0) {
                    edges[k] = new int[]{
                            i * cn + j - 1,
                            i * cn + j,
                            Math.abs(heights[i][j - 1] - heights[i][j])
                    };
                    k++;
                }
            }
        }
        int ufn = rn * cn, min = 0;
        UF uf = new UF(ufn);
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        for (int i = 0; i < en; i++) {
            uf.union(edges[i][0], edges[i][1]);
            if (uf.isConnected(0, ufn - 1)) {
                min = edges[i][2];
                break;
            }
        }
        return min;
    }
    public static void main(String[] args) {
        int[][] heights = new int[][]{{1,2,2},{3,8,2},{5,3,5}};
        int res = minimumEffortPath(heights);
        System.out.println(res);
    }
}
