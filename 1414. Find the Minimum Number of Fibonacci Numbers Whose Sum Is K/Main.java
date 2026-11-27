import java.util.*;


class Solution {
    int find(int target, List<Integer> nums) {
        int l = 0, r = nums.size(), m, cur, max = 1;
        while (l < r) {
            m = (l + r) / 2;
            cur = nums.get(m);
            if (cur == target) {
                return cur;
            }
            if (cur < target) {
                max = Math.max(max, cur);
                l = m + 1;
            } else {
                r = m;
            }
        }
        return max;
    }
    public int findMinFibonacciNumbers(int k) {
        if (k == 1) {
            return 1;
        }
        List<Integer> fib = new ArrayList<>();
        int n1 = 1, n2 = 1, n3, next = n1 + n2;
        fib.add(1);
        while (next <= k) {
            n3 = next;
            fib.add(n3);
            n1 = n2;
            n2 = n3;
            next = n1 + n2;
        }
        int i = 0, j, n = fib.size(), cnt = 0, remain = k, largest;
        while (remain > 0) {
            largest = find(remain, fib);
            remain -= largest;
            cnt++;
        }
        return cnt;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int res = s.findMinFibonacciNumbers(19);
      System.out.println(res);
  }
}
