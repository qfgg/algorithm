import java.util.*;


class Solution {
    public int takeCharacters(String s, int k) {
        char[] chars = s.toCharArray();
        int l = 0, r = 0, n = chars.length, max = 0;
        int[] total = new int[3];
        for (char ch : chars) {
            total[ch - 'a']++;
        }
        for (int t : total) {
            if (t < k) {
                return -1;
            }
        }
        total[chars[r] - 'a']--;
        while (r < n) {
            if (total[0] >= k && total[1] >= k && total[2] >= k) {
                r++;
                if (r < n) {
                    total[chars[r] - 'a']--;
                }
                max = Math.max(max, r - l);
            } else {
                total[chars[l] - 'a']++;
                l++;
                if (r < l) {
                    r++;
                    if (r < n) {
                        total[chars[r] - 'a']--;
                    }
                }
            }
        }
        return n - max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "abc";
        int k = 1;
        int res = s.takeCharacters(str, k);
        System.out.println(res);
    }
}
