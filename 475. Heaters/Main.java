import java.util.*;


class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int min = 0, i = 0, j = 0, n1 = houses.length, n2 = heaters.length;
        if (houses[0] < heaters[0]) {
            min = heaters[0] - houses[0];
            i++;
        }
        if (houses[n1 - 1] > heaters[n2 - 1]) {
            min = Math.max(min, houses[n1 - 1] - heaters[n2 - 1]);
        }
        while (i < n1 && houses[i] < heaters[j]) {
            i++;
        }
        while (j + 1 < n2 && houses[i] >= heaters[j + 1]) {
            j++;
        }
        while (i < n1 && j + 1 < n2 && houses[i] < heaters[j + 1]) {
            min = Math.max(min, Math.min(houses[i] - heaters[j], heaters[j + 1] - houses[i]));
            i++;
            while (i < n1 && j + 1 < n2 && houses[i] >= heaters[j + 1]) {
                j++;
            }
        }
        return min;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] houses = new int[]{1010,1441,2824,4578,4587,4702,6226,9849}, heaters = new int[]{165,742,1148,1154,1375,1435,4412,7844,8233,8235};
      int res = s.findRadius(houses, heaters);
      System.out.println(res);
  }
}
