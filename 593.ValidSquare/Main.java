import java.util.*;

public class Main {
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = new int[4][];
        p[0] = p1;
        p[1] = p2;
        p[2] = p3;
        p[3] = p4;
        int[] up = p[0], down = p[0], left = p[0], right = p[0];
        int i;
        for (i = 1; i < 4; i++) {
            if (p[i][0] < left[0] || (p[i][0] == left[0] && p[i][1] > left[1])) {
                left = p[i];
            }
            if (p[i][0] > right[0] || (p[i][0] == right[0] && p[i][1] < right[1])) {
                right = p[i];
            }
            if (p[i][1] < down[1] || (p[i][1] == down[1] && p[i][0] < down[0])) {
                down = p[i];
            }
            if (p[i][1] > up[1] || (p[i][1] == up[1] && p[i][0] > up[0])) {
                up = p[i];
            }
        }
        if (up == down || up == left || up == right ||
                down == right || down == left || left == right) {
            return false;
        }
        return down[0] - left[0] == up[1] - left[1] &&
                up[1] - left[1] == right[0] - up[0] &&
                right[0] - up[0] == right[1] - down[1] &&
                left[1] - down[1] == up[0] - left[0] &&
                up[0] - left[0] == up[1] - right[1] &&
                up[1] - right[1] == right[0] - down[0];
    }
    public static void main(String[] args) {
        int[] p1 = new int[]{0,0}, p2 = new int[]{1,1}, p3 = new int[]{1,0}, p4 = new int[]{0,1};
        boolean ans = validSquare(p1, p2, p3, p4);
        System.out.println(ans);
    }
}
