import java.util.*;

public class Main {
    public static int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> startSet = new HashSet<>();
        int i, j, l, cnt = 0, tmp;
        for (String sw : startWords) {
            l = sw.length();
            tmp = 0;
            for (i = 0; i < l; i++) {
                tmp |= (1 << (sw.charAt(i) - 'a'));
            }
            startSet.add(tmp);
        }
        for (String tw : targetWords) {
            l = tw.length();
            for (i = 0; i < l; i++) {
                tmp = 0;
                for (j = 0; j < l; j++) {
                    if (j != i) {
                        tmp |= (1 << (tw.charAt(j) - 'a'));
                    }
                }
                if (startSet.contains(tmp)) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        String[] startWords = new String[]{"ant","act","tack"};
        String[] targetWords = new String[]{"tack","act","acti"};
        int res = wordCount(startWords, targetWords);
        System.out.println(res);
    }
}
