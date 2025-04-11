import java.util.*;

class Solution {
    private static List<String> getStrings(int n, int[] x) {
        StringBuilder sb = new StringBuilder();
        List<String> sl = new ArrayList<>();
        for (int i = 1; i < x.length; i++) {
            for (int j = 0; j < n; j++) {
                if (j == x[i] - 1) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            sl.add(sb.toString());
            sb.setLength(0);
        }
        return sl;
    }
    private void search(int n, int row, int[] x, List<List<String>> ret) {
        int i, j;
        if (row == 1) {
            for (i = 1; i <= n; i++) {
                x[1] = i;
                if (1 == n) {
                    ret.add(getStrings(n, x));
                } else {
                    search(n, 2, x, ret);
                }
                x[1] = 0;
            }
        } else {
            for (i = 1; i <= n; i++) {
                for (j = 1; j < row; j++) {
                    if (x[j] == i || row - j == i - x[j] || row - j == x[j] - i) {
                        break;
                    }
                }
                if (j == row) {
                    x[row] = i;
                    if (row == n) {
                        ret.add(getStrings(n, x));
                    } else {
                        search(n, row + 1, x, ret);
                    }
                    x[row] = 0;
                }
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        int[] x = new int[n + 1];
        List<List<String>> ret = new ArrayList<>();
        search(n, 1, x, ret);
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<String>> res = s.solveNQueens(4);
        for (List<String> row : res) {
            System.out.println(row);
            System.out.println();
        }
    }
}
