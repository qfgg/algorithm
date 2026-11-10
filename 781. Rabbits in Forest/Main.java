import java.util.*;


class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int i, n = answers.length, j, k, total = 0;
        for (i = 0; i < n; i++) {
            if (cnt.containsKey(answers[i])) {
                cnt.put(answers[i], cnt.get(answers[i]) + 1);
            } else {
                cnt.put(answers[i], 1);
            }
        }
        for (Integer other : cnt.keySet()) {
            j = cnt.get(other);
            k = other + 1;
            total += (j / k + (j % k == 0 ? 0 : 1)) * k; 
        }
        return total;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] answers = new int[]{1,1,2};
      int res = s.numRabbits(answers);
      System.out.println(res);
  }
}
