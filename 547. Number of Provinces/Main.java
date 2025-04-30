import java.util.*;


class UF {
        int[] p;
        int[] size;
        int groupCount;
        UF(int n) {
            p = new int[n];
            size = new int[n];
            groupCount = n;
            for (int i = 0; i < n; i++) {
                p[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x) {
            if (p[x] == x) {
                return x;
            }
            p[x] = find(p[x]);
            return p[x];
        }
        public void union(int x, int y) {
            int xroot = find(x);
            int yroot = find(y);
            if (xroot == yroot) {
                return;
            }
            groupCount--;
            if (size[xroot] >= size[yroot]) {
                p[yroot] = xroot;
                size[xroot] += size[yroot];
            } else {
                p[xroot] = yroot;
                size[yroot] += size[xroot];
            }
        }
        public int getGroupCount() {
            return groupCount;
        }
}

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, i, j;
        UF set = new UF(n);
        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    set.union(i, j);
                }
            }
        }
        return set.getGroupCount();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] isConnected = new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,1},
        };
        int res = s.findCircleNum(isConnected);
        System.out.println(res);
    }
}
