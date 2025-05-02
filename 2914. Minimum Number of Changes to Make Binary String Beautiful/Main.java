import java.util.*;


class Solution {
    public int minChanges(String s) {
        List<Boolean> isOdd = new ArrayList<>();
        char[] chars = s.toCharArray();
        int curCount = 1, i, n = chars.length;
        for (i = 1; i < n; i++) {
            if (chars[i] != chars[i - 1]) {
                isOdd.add(curCount % 2 == 1);
                curCount = 1;
            } else {
                curCount++;
            }
        }
        isOdd.add(curCount % 2 == 1);
        int min = 0, l = isOdd.size(), startIdx = -1;
        for (i = 0; i < l; i++) {
            if (isOdd.get(i)) {
                if (startIdx == -1) {
                    startIdx = i;
                } else {
                    min += i - startIdx;
                    startIdx = -1;
                }
            }
        }
        return min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "001110000110010000";
        int res = s.minChanges(str);
        System.out.println(res);
    }
}
