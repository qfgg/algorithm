import java.util.*;


class Solution {
    public int countPrimes(int n) {
        int i, count = 0, p;
        boolean[] isNotPrime = new boolean[n + 1];
        for (i = 2; i < n; i++) {
            if (!isNotPrime[i]) {
                count++;
                p = i;
                p += i;
                while (p < n) {
                    isNotPrime[p] = true;
                    p += i;
                }
            }
        }
        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 10;
        long res = s.countPrimes(n);
        System.out.println(res);
    }
}
