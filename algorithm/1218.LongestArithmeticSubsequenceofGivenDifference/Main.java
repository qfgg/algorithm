import java.util.*;

public class Main {
    public static int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> sizeMap = new HashMap<>();
        int max = 1, tmpL;
        for (int e : arr) {
            if (sizeMap.containsKey(e - difference)) {
                tmpL = sizeMap.get(e - difference) + 1;
            } else {
                tmpL = 1;
            }
            sizeMap.put(e, tmpL);
            if (tmpL > max) {
                max = tmpL;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{3,0,-3,4,-4,7,6};
        int difference = 3;
        int res = longestSubsequence(arr, difference);
        System.out.println(res);
    }
}
