import java.util.*;


public class Main {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int pLen = p.length(), sLen = s.length();
        if (sLen < pLen) {
            return ans;
        }
        int[] cnt = new int[26];
        int i, j, total = 0, curTotal = 0;
        char cur;
        for (i = 0; i < pLen; i++) {
            cur = p.charAt(i);
            cnt[cur - 'a']++;
            total++;
        }
        i = 0;
        j = 0;
        int[] cnt1 = new int[26];
        while (j < sLen) {
            cur = s.charAt(j);
            if (cnt1[cur - 'a'] < cnt[cur - 'a']) {
                cnt1[cur - 'a']++;
                curTotal++;
                if (curTotal == total) {
                    ans.add(i);
                    cnt1[s.charAt(i) - 'a']--;
                    i++;
                    curTotal--;
                }
                j++;
            } else if (cnt[cur - 'a'] == 0) {
                i = j + 1;
                Arrays.fill(cnt1, 0);
                curTotal = 0;
            } else {
                cnt1[s.charAt(i) - 'a']--;
                i++;
                curTotal--;
            }
            if (i > j) {
                j = i;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String s = "aaab", p = "aab";
        List<Integer> ans = findAnagrams(s, p);
        System.out.println(ans);
    }
}
