import java.util.*;


public class Main {
    public static int racecar(int target) {
        int b = 1, k = 0;
        while (b - 1 < target) {
            b = b << 1;
            k++;
        }
        int[] f = new int[b + 10];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        f[1] = 1;
        f[2] = 4;
        f[3] = 2;
        f[7] = 3;
        if (f[target] < Integer.MAX_VALUE) {
            return f[target];
        }
        int pre = 3, next = 7, j;
        k = 3;
        for (int i = 4; i <= target; i++) {
            if (i == next) {
                pre = next;
                next = 2 * pre + 1;
                k++;
                if (i == target) {
                    return k - 1;
                }
                f[next] = k;
            } else {
                f[i] = Math.min(f[i], f[next] + 1 + f[next - i]);
                for (j = 0; j < k; j++) {
                    f[i] = Math.min(f[i], j == 0 ? f[pre] + 2 + f[i - pre] : f[pre] + 2 + f[2 * j - 1] + f[i - pre + 2 * j - 1]);
                }
            }
        }
        return f[target];
    }
    public static void main(String[] args) {
        int target = 1;
        int res = racecar(target);
        System.out.println(res);
    }
}
