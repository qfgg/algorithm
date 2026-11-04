import java.util.*;


class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int k, i, n = arr.length, mod = 1000000007;
        long inc, total = n;
        Integer j;
        long[] cnt = new long[n];
        Arrays.fill(cnt, 1);
        Map<Integer, Integer> valIdx = new HashMap<>();
        for (i = 0; i < n; i++) {
            valIdx.put(arr[i], i);
        }
        for (k = 1; k < n; k++) {
            for (i = 0; i < k; i++) {
                if (arr[k] % arr[i] == 0 && valIdx.containsKey(arr[k] / arr[i])) {
                    j = valIdx.get(arr[k] / arr[i]);
                    inc = cnt[i] * cnt[j];
                    cnt[k] = (cnt[k] + inc) % mod;
                    total = (total + inc) % mod;
                }
            }
        }
        return (int)total;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] arr = new int[]{12,6,4,2,3};
      int res = s.numFactoredBinaryTrees(arr);
      System.out.println(res);
  }
}

