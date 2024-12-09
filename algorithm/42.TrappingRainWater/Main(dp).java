import java.util.*;


public class Main {
    public static int trap(int[] height) {
        int l = height.length;
        int[] f = new int[l];
        for (int i = 1; i < l; i++) {
            f[i] = Math.max(f[i - 1] + height[i - 1] - height[i], 0);
        }
        int water = 0, r = f[l - 1];
        for (int i = l - 2; i >= 0; i--) {
            if (r < f[i]) {
                water += f[i] - r;
            } else {
                r = f[i];
            }
        }
        return water;
    }
    public static void main(String[] args) {
        int[] height = new int[]{5,2,1,2,1,5};
        int res = trap(height);
        System.out.println(res);
    }
}
