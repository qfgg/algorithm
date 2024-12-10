import java.util.*;


public class Main {
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length, j, rangeMax;
        int[] f = new int[n];
        f[0] = arr[0];
        for (int i = 1; i < n; i++) {
            j = i;
            rangeMax = arr[j];
            while (j >= 0 && i - j < k) {
                if (arr[j] >= rangeMax) {
                    rangeMax = arr[j];
                }
                f[i] = Math.max(f[i], (j > 0 ? f[j - 1] : 0) + (i - j + 1) * rangeMax);
                j--;
            }
        }
        return f[n - 1];
    }
    public static void main(String[] args) {
        int[] arr = new int[]{0, 3, 5, 4, 10, 4, 3, 2, 4, 6};
        int k = 4;
        int ans;
        ans = maxSumAfterPartitioning(arr, k);
        System.out.println(ans);
    }
}
