import java.util.*;


public class Main {
    public static int findEnd(int[] arr, int start) {
        int i = start, end = arr[start];
        while (i < end) {
            i++;
            if (arr[i] > end) {
                end = arr[i];
            }
        }
        return end;
    }
    public static int maxChunksToSorted(int[] arr) {
        int len = arr.length, start = 0, count = 0;
        while (start < len) {
            start = findEnd(arr, start) + 1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,0,3};
        int ans = maxChunksToSorted(arr);
        System.out.println(ans);
    }
}
