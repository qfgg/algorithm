import java.util.*;


class Solution {
    public String getSmallestString(int n, int k) {
        int remain = k - n, z = remain / 25, last = remain % 25, a = n - z;
        StringBuilder sb = new StringBuilder();
        if (last > 0) {
            a--;
        }
        sb.repeat('a', a);
        if (last > 0) {
            sb.append((char)(last + 'a'));
        }
        if (z > 0) {
            sb.repeat('z', z);
        }
        return sb.toString();
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      String res = s.getSmallestString(5, 73);
      System.out.println(res);
  }
}
