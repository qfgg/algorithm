import java.util.*;


class Solution {
    int h = -1, m = -1;
    boolean isLarger(int[] a) {
        int ah = a[0] * 10 + a[1], am = a[2] *10 + a[3];
        if (ah > 23 || am > 59) {
            return false;
        }
        if (h == -1) {
            h = ah;
            m = am;
            return true;
        }
        if (ah < h) {
            return false;
        }
        if (ah > h) {
            h = ah;
            m = am;
            return true;
        }
        if (am < m) {
            return false;
        }
        if (am > m) {
            h = ah;
            m = am;
            return true;
        }
        return false;
    }
    void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
    void dfs(int[] arr, int startIdx) {
        if (startIdx == 3) {
            isLarger(arr);
            return;
        }
        dfs(arr, startIdx + 1);
        for (int i = startIdx + 1; i < 4; i++) {
            swap(arr, startIdx, i);
            dfs(arr, startIdx + 1);
            swap(arr, startIdx, i);
        }
    }
    public String largestTimeFromDigits(int[] arr) {
        dfs(arr, 0);
        if (h == -1) {
            return "";
        }
        String hour = h < 10 ? "0" + h : Integer.toString(h), minute = m < 10 ? "0" + m : Integer.toString(m);
        return hour + ":" + minute;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] arr = new int[]{1,2,3,4};
      String res = s.largestTimeFromDigits(arr);
      System.out.println(res);
  }
}
