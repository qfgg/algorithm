import java.util.*;


public class Main {
    public static int[] findDiagonalOrder(int[][] mat) {
        int ymax = mat.length, xmax = mat[0].length, dir = 0;
        int[] path = new int[ymax * xmax];
        int[] offset = new int[]{1, -1, 1};
        int y = 0, x = 0, ny, nx, i = 0;
        while (true) {
            path[i] = mat[y][x];
            if (x == xmax - 1 && y == ymax - 1) {
                break;
            }
            nx = x + offset[dir];
            ny = y + offset[dir + 1];
            if (ny < 0) {
                if (x == xmax - 1 && y == 0) {
                    y++;
                } else {
                    x++;
                }
                dir = 1;
            } else if (nx < 0) {
                if (x == 0 && y == ymax - 1) {
                    x++;
                } else {
                    y++;
                }
                dir = 0;
            } else if (nx == xmax) {
                y++;
                dir = 1;
            } else if (ny == ymax) {
                x++;
                dir = 0;
            } else {
                x = nx;
                y = ny;
            }
            i++;
        }
        return path;
    }
    public static void main(String[] args) {
        int[][] mat = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[] ans = findDiagonalOrder(mat);
        System.out.println(Arrays.toString(ans));
    }
}
