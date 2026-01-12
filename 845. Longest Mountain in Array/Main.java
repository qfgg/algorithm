import java.util.*;


class Solution {
    public int longestMountain(int[] arr) {
        boolean isAsc = false, isDesc = false;
        int max = 0, n = arr.length, i, cur = 1;
        for (i = 1; i < n; i++) {
            if (isDesc) {
                if (arr[i - 1] > arr[i]) {
                    cur++;
                } else {
                    isDesc = false;
                    max = Math.max(max, cur);
                    if (arr[i - 1] == arr[i]) {
                        cur = 1;
                    } else {
                        isAsc = true;
                        cur = 2;
                    }
                }
            } else {
                if (arr[i - 1] < arr[i]) {
                    isAsc = true;
                    cur++;
                } else if (arr[i - 1] == arr[i]) {
                    isAsc = false;
                    cur = 1;
                } else {
                    if (isAsc) {
                        isAsc = false;
                        isDesc = true;
                        cur++;
                    }
                }
            }
        }
        if (cur > 2 && isDesc) {
            max = Math.max(max, cur);
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[]{0,1,2,3,4,5,4,3,2,1,0};
        int res = s.longestMountain(arr);
        System.out.println(res);
    }
}
