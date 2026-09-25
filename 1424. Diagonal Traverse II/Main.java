import java.util.*;


class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<int[]> pos = new ArrayList<>();
        List<Integer> row;
        int m = nums.size(), n, i, j;
        for (i = 0; i < m; i++) {
            row = nums.get(i);
            n = row.size();
            for (j = 0; j < n; j++) {
                pos.add(new int[]{i + j, i, j});
            }
        }
        pos.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });
        int[] ret = new int[pos.size()];
        i = 0;
        for (int[] p : pos) {
            ret[i++] = nums.get(p[1]).get(p[2]);
        }
        return ret;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      List<List<Integer>> nums = new ArrayList<>();
      List<Integer> r1 = new ArrayList<>(List.of(1,2,3));
      List<Integer> r2 = new ArrayList<>(List.of(4,5,6));
      List<Integer> r3 = new ArrayList<>(List.of(7,8,9));
      nums.add(r1);
      nums.add(r2);
      nums.add(r3);
      int[] ret = s.findDiagonalOrder(nums);
      System.out.println(Arrays.toString(ret));
  }
}
