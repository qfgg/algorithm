import java.util.*;


class Solution {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> count = new HashMap<>();
        int i, n = tasks.length, num, round = 0;
        for (i = 0; i < n; i++) {
            if (count.containsKey(tasks[i])) {
                count.put(tasks[i], count.get(tasks[i]) + 1);
            } else {
                count.put(tasks[i], 1);
            }
        }
        for (Integer task : count.keySet()) {
            num = count.get(task);
            if (num == 1) {
                return -1;
            }
            round += (num + 2) / 3;
        }
        return round;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] tasks = new int[]{2,2,3,3,2,4,4,4,4,4};
      int res = s.minimumRounds(tasks);
      System.out.println(res);
  }
}
