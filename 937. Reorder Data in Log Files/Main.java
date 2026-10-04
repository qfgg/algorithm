import java.util.*;


class Solution {
    public String[] reorderLogFiles(String[] logs) {
        int i, j, k, n = logs.length, len;
        String[] ret = new String[n];
        List<String[]> letterLogs = new ArrayList<>();
        char next;
        String[] letterLog;
        for (i = n - 1, k = n - 1; i >= 0; i--) {
            len = logs[i].length();
            for (j = 0; j < len; j++) {
                if (logs[i].charAt(j) == ' ') {
                    next = logs[i].charAt(j + 1);
                    if (next >= 'a' && next <= 'z') {
                        letterLog = new String[2];
                        letterLog[0] = logs[i].substring(0, j);
                        letterLog[1] = logs[i].substring(j + 1, len);
                        letterLogs.add(letterLog);
                    } else {
                        ret[k--] = logs[i];
                    }
                    break;
                }
            }
        }
        letterLogs.sort((a, b) -> {
            int order = a[1].compareTo(b[1]);
            return order == 0 ? a[0].compareTo(b[0]) : order;
        });
        for (i = 0; i <= k; i++) {
            letterLog = letterLogs.get(i);
            ret[i] = String.join(" ", letterLog);
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] logs = new String[]{
                "dig1 8 1 5 1",
                "let1 art can",
                "dig2 3 6",
                "let2 own kit dig",
                "let3 art zero"
        };
        String[] res = s.reorderLogFiles(logs);
        System.out.println(Arrays.toString(res));
    }
}
