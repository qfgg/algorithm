import java.util.*;


class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length, i, max = 0, leftMax;
        int[] arr1 =  new int[n], arr2 = new int[n];
        for (i = 0; i < n; i++) {
            arr1[i] = values[i] + i;
            arr2[i] = values[i] - i;
        }
        leftMax = arr1[0];
        for (i = 1; i < n; i++) {
            max = Math.max(max, leftMax + arr2[i]);
            leftMax = Math.max(leftMax, arr1[i]);
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] values = new int[]{8,1,5,2,6};
        int res = s.maxScoreSightseeingPair(values);
        System.out.println(res);
    }
}
