import java.util.*;


class Solution {
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 == 1) {
            return new int[0];
        }
        int i, j = 0, cnt0 = 0, remain = 0;
        int[] res = new int[n / 2];
        HashMap<Integer, Integer> count = new HashMap<>();
        for (i = 0; i < n; i++) {
            if (changed[i] == 0) {
                cnt0++;
            } else {
                count.put(changed[i], count.getOrDefault(changed[i], 0) + 1);
                remain++;
            }
        }
        if (cnt0 % 2 == 1) {
            return new int[0];
        }
        Arrays.sort(changed);
        for (i = 0; i < n; i++) {
            if (changed[i] > 0 && count.containsKey(changed[i]) && count.get(changed[i]) > 0) {
                if (count.containsKey(changed[i] * 2) && count.get(changed[i] * 2) > 0) {
                    res[j] = changed[i];
                    j++;
                    count.put(changed[i], count.get(changed[i]) - 1);
                    count.put(changed[i] * 2, count.get(changed[i] * 2) - 1);
                    remain = remain - 2;
                }
            }
        }
        if (remain == 0) {
            return res;
        }
        return new int[0];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] changed = new int[]{4,4,16,20,8,8,2,10};
        int[] res = s.findOriginalArray(changed);
        System.out.println(Arrays.toString(res));
    }
}
