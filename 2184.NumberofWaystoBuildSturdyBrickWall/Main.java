import java.util.*;


public class Main {
    public static void buildRow(int width, int[] bricks, int w, List<Integer> row, List<List<Integer>> rows) {
        if (w == width) {
            rows.add(List.copyOf(row));
            return;
        }
        for (int bk : bricks) {
            if (bk + w <= width) {
                row.add(bk);
                buildRow(width, bricks, w + bk, row, rows);
                row.remove(row.size() - 1);
            }
        }
    }

    public static boolean check(List<Integer> r1, List<Integer> r2) {
        if (r1.size() == 1 && r2.size() == 1) {
            return true;
        }
        Set<Integer> presum = new HashSet<>();
        int n1 = r1.size(), n2 = r2.size(), pre = 0, i;
        for (i = 0; i < n1 - 1; i++) {
            pre += r1.get(i);
            presum.add(pre);
        }
        pre = 0;
        for (i = 0; i < n2 - 1; i++) {
            pre += r2.get(i);
            if (presum.contains(pre)) {
                return false;
            }
        }
        return true;
    }
    public static int buildWall(int height, int width, int[] bricks) {
        List<Integer> row = new ArrayList<>();
        List<List<Integer>> rows = new ArrayList<>();
        buildRow(width, bricks, 0, row, rows);
        int n = rows.size(), i , j, k;
        boolean[][] canDo = new boolean[n][n];
        for (i = 0; i < n; i++) {
            for (j = i; j < n; j++) {
                canDo[i][j] = check(rows.get(i), rows.get(j));
                canDo[j][i] = canDo[i][j];
            }
        }
        int[][] f = new int[height][n];
        Arrays.fill(f[0], 1);
        for (i = 1; i < height; i++) {
            for (j = 0; j < n; j++) {
                for (k = 0; k < n; k++) {
                    if (canDo[j][k]) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % 1000000007;
                    }
                }
            }
        }
        int sum = 0;
        for (i = 0; i < n; i++) {
            sum = (sum + f[height - 1][i]) % 1000000007;
        }
        return sum;
    }
    public static void main(String[] args) {
        int height = 100, width = 10;
        int[] bricks = new int[]{1,2,3,4,5,6,7,8,9,10};
        int res;
        res = buildWall(height, width, bricks);
        System.out.println(res);
    }
}
