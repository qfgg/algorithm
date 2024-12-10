import java.util.*;


public class Main {
    public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int left = Math.min(rec1[0], rec2[0]);
        int bottom = Math.min(rec1[1], rec2[1]);
        int right = Math.max(rec1[2], rec2[2]);
        int top = Math.max(rec1[3], rec2[3]);
        int xdiff = rec1[2] - rec1[0] - right + left + rec2[2] - rec2[0];
        int ydiff = rec1[3] - rec1[1] - top + bottom + rec2[3] - rec2[1];
        return xdiff > 0 && ydiff > 0;
    }
    public static void main(String[] args) {
        int[] rec1 = new int[]{-257926405,-680763313,702840196,454409669};
        int[] rec2 = new int[]{-275916328,-417802221,22808107,675629469};
        boolean ans = isRectangleOverlap(rec1, rec2);
        System.out.println(ans);
    }
}
