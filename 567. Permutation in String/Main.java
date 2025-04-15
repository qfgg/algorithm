import java.util.*;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> s1Count = new HashMap<>();
        int l1 = s1.length(), l2 = s2.length(), i, j, left = -1, n = 0;
        char cur;
        for (i = 0; i < l1; i++) {
            s1Count.put(s1.charAt(i), s1Count.getOrDefault(s1.charAt(i), 0) + 1);
        }
        for (i = 0; i < l2; i++) {
            cur = s2.charAt(i);
            if (s1Count.containsKey(cur)) {
                s1Count.put(cur, s1Count.get(cur) - 1);
                n++;
                if (s1Count.get(cur) >= 0) {
                    if (left == -1) {
                        left = i;
                    }
                    if (n == l1) {
                        return true;
                    }
                } else {
                    j = left;
                    while (s1Count.get(cur) < 0 && j < l2) {
                        s1Count.put(s2.charAt(j), s1Count.get(s2.charAt(j)) + 1);
                        n--;
                        j++;
                    }
                    left = j;
                }
            } else {
                if (left > -1) {
                    j = left;
                    while (j < i) {
                        s1Count.put(s2.charAt(j), s1Count.get(s2.charAt(j)) + 1);
                        n--;
                        j++;
                    }
                }
                left = -1;
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String s1 = "adc", s2 = "dcda";
        boolean res = s.checkInclusion(s1, s2);
        System.out.println(res);
    }
}
