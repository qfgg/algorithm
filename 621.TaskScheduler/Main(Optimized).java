import java.util.*;

public class Main {
    public static int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        int max = 0;
        char mc = '\u0000', t;
        for (char t0 : tasks) {
            cnt[t0 - 'A']++;
            if (cnt[t0 - 'A'] > max) {
                max = cnt[t0 - 'A'];
                mc = t0;
            }
        }
        int idle = n * (max - 1), itv = max + idle, cur;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) {
                continue;
            }
            t = (char)('A' + i);
            if (t != mc) {
                cur = cnt[t - 'A'];
                if (idle < cur) {
                    itv += cur - idle;
                    idle = 0;
                } else {
                    if (cur == max) {
                        idle -= max - 1;
                        itv++;
                    } else {
                        idle -= cur;
                    }
                }
            }
        }
        return itv;
    }
    public static void main(String[] args) {
        char[] tasks = new char[]{'A', 'A', 'A', 'A', 'C', 'D', 'D', 'E', 'E', 'F', 'F', 'G'};
        int res = leastInterval(tasks, 2);
        System.out.println(res);
    }
}
