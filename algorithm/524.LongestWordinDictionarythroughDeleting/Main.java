import java.util.*;

class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        Queue<int[]>[] queues = new Queue[26];
        int i, n = dictionary.size(), size;
        char c;
        int[] cur;
        String res = "", can;
        for (i = 0; i < n; i++) {
            c = dictionary.get(i).charAt(0);
            if (queues[c - 'a'] == null) {
                queues[c - 'a'] = new LinkedList();
            }
            queues[c - 'a'].add(new int[]{i, 0});
        }
        n = s.length();
        for (i = 0; i < n; i++) {
            c = s.charAt(i);
            if (queues[c - 'a'] != null && !queues[c - 'a'].isEmpty()) {
                size = queues[c - 'a'].size();
                while (size != 0) {
                    cur = queues[c - 'a'].poll();
                    can = dictionary.get(cur[0]);
                    if (cur[1] == can.length() - 1) {
                        if (res.isEmpty() ||
                                can.length() > res.length() ||
                                (can.length() == res.length() && can.compareTo(res) < 0)) {
                            res = can;
                        }
                    } else {
                        if (queues[can.charAt(cur[1] + 1) - 'a'] == null) {
                            queues[can.charAt(cur[1] + 1) - 'a'] = new LinkedList<>();
                        }
                        queues[can.charAt(cur[1] + 1) - 'a'].add(new int[]{cur[0], cur[1] + 1});
                    }
                    size--;
                }
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "abpcplea";
        List<String> dictionary = new ArrayList<>(Arrays.asList("ale","apple","monkey","plea"));
        String res = s.findLongestWord(str, dictionary);
        System.out.println(res);
    }
}
