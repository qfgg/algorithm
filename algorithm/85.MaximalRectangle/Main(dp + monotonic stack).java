import java.util.*;


public class Main {
    public static int findMax(int i, int j, int[][] f) {
        // each bar width 1. 0: height, 1: accumulated width from other bar
        Deque<int[]> ms = new ArrayDeque<>();
        int k = i, max = 0, area;
        int[] top, preTop, next;
        while (k >= 0 && f[k][j] > 0) {
            next = new int[]{f[k][j], 0};
            if (ms.isEmpty()) {
                ms.push(next);
            } else {
                preTop = ms.peek();
                while (next[0] < preTop[0]) {
                    ms.pop();
                    area = preTop[0] * (preTop[1] + next[1] + 1);
                    if (area > max) {
                        max = area;
                    }
                    next[1] += preTop[1] + 1;
                    if (ms.isEmpty()) {
                        break;
                    }
                    preTop = ms.peek();
                }
                if (next[0] == preTop[0]) {
                    preTop[1] += next[1] + 1;
                } else {
                    ms.push(next);
                }
            }
            k--;
        }
        while (!ms.isEmpty()) {
            preTop = ms.pop();
            area = preTop[0] * (preTop[1] + 1);
            if (area > max) {
                max = area;
            }
            top = ms.peek();
            if (top != null) {
                top[1] += preTop[1] + 1;
            }
        }
        return max;
    }
    public static int maximalRectangle(char[][] matrix) {
        int rn = matrix.length, cn = matrix[0].length, i, j, max = 0;
        int[][] f = new int[rn][cn];
        for (i = 0; i < rn; i++) {
            for (j = 0; j < cn; j++) {
                if (matrix[i][j] == '1') {
                    f[i][j] = j > 0 ? f[i][j - 1] + 1 : 1;
                    if (i == rn - 1 || matrix[i + 1][j] == '0') {
                        max = Math.max(max, findMax(i, j, f));
                    }
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        int res = maximalRectangle(matrix);
        System.out.println(res);
    }
}
