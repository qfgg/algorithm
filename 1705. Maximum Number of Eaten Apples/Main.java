import java.util.*;


class Solution {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int end = apples.length, i = 0, sum = 0;
        int[] cur;
        while (i < end || !q.isEmpty()) {
            if (i < end && apples[i] > 0) {
                q.add(new int[]{i, i + days[i]});
            }
            while (!q.isEmpty()) {
                cur = q.poll();
                if (cur[1] <= i) {
                    continue;
                }
                if (apples[cur[0]] > 0) {
                    apples[cur[0]]--;
                    sum++;
                    if (apples[cur[0]] > 0) {
                        q.add(cur);
                    }
                    break;
                }
            }
            i++;
        }
        return sum;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] apples = new int[]{1,2,3,5,2};
      int[] days = new int[]{3,2,1,4,2};
      int res = s.eatenApples(apples, days);
      System.out.println(res);
  }
}
