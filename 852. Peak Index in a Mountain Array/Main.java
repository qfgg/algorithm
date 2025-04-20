import java.util.*;


class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length, m;
        while (l < r) {
            m = (l + r) / 2;
            if (m > 0 && m < arr.length && arr[m - 1] < arr[m] && arr[m] > arr[m + 1]) {
                return m;
            }
            if (m == 0 || (arr[m - 1] < arr[m] && arr[m] < arr[m + 1])) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[]{0,2,1,0};
        int res = s.peakIndexInMountainArray(arr);
        System.out.println(res);
    }
}
