import java.util.*;

public class Main {
    public static int numMatchingSubseq(String s, String[] words) {
        Queue<int[]>[] letter = new Queue[26];
        int i, idx, n = words.length, sn = s.length(), cnt = 0, oldSize;
        for (i = 0; i < n; i++) {
            idx = words[i].charAt(0) - 'a';
            if (letter[idx] == null) {
                letter[idx] = new LinkedList<>();
            }
            letter[idx].add(new int[]{i, 0});
        }
        int[] cur;
        for (i = 0; i < sn; i++) {
            idx = s.charAt(i) - 'a';
            if (letter[idx] != null) {
                oldSize = letter[idx].size();
                while (oldSize > 0) {
                    cur = letter[idx].poll();
                    if (words[cur[0]].length() - 1 == cur[1]) {
                        cnt++;
                    } else {
                        cur[1]++;
                        if (letter[words[cur[0]].charAt(cur[1]) - 'a'] == null) {
                            letter[words[cur[0]].charAt(cur[1]) - 'a'] = new LinkedList<>();
                        }
                        letter[words[cur[0]].charAt(cur[1]) - 'a'].add(cur);
                    }
                    oldSize--;
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        String s = "abcde";
        String[] words = new String[]{"a","bb","acd","ace"};
        int res = numMatchingSubseq(s, words);
        System.out.println(res); //2
    }
}
