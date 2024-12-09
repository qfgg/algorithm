import java.util.*;

public class Main {
    public static int shortestWay(String source, String target) {
        int[] firstIdx = new int[26];
        Arrays.fill(firstIdx, -1);
        char[] src = source.toCharArray();
        int srclen = src.length, tglen = target.length(), i, j, cnt = 0;
        char cur;
        boolean inSub = false;
        for (i = 0; i < srclen; i++) {
            if (firstIdx[src[i]-'a'] == -1) {
                firstIdx[src[i]-'a'] = i;
            }
        }
        j = 0;
        while (j < tglen) {
            cur = target.charAt(j);
            if (firstIdx[cur - 'a'] == -1) {
                return -1;
            }
            if (!inSub) {
                i = firstIdx[cur - 'a'];
                inSub = true;
                j++;
            } else {
                i++;
                while (i < srclen && src[i] != cur) {
                    i++;
                }
                if (i == srclen) {
                    cnt++;
                    inSub = false;
                } else {
                    j++;
                }
            }
        }
        return cnt + 1;
    }
    public static void main(String[] args) {
        char[] set = new char[]{'a', 'y', 'd', 'e', 'j', 'q', 'l', 'o'};
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        String s1 = "ayaoodejqloj";
        for (int i = 0; i < 1000; i++) {
            sb.append(set[rand.nextInt(8)]);
        }
        String s2 = sb.toString();
        int ans = shortestWay(s1, s2);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(ans);
    }
}
