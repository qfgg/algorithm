import java.util.*;


class Solution {
    public String reorganizeString(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int i, n = chars.length;
        int[] count = new int[26];
        for (i = 0; i < n; i++) {
            count[chars[i] - 'a']++;
        }
        // [idx, count], idx of int[] count and current count of this idx
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        Queue<int []> tmp = new LinkedList<>();
        for (i = 0; i < 26; i++) {
            if (count[i] > 0) {
                heap.add(new int[]{i, count[i]});
            }
        }
        char pre = 0, most;
        int[] cur;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            if (cur[1] != count[cur[0]]) {
                continue;
            }
            most = (char)('a' + cur[0]);
            while (most == pre && !heap.isEmpty()) {
                tmp.add(cur);
                cur = heap.poll();
                most = (char)('a' + cur[0]);
            }
            if (most != pre) {
                sb.append(most);
                pre = most;
                count[cur[0]]--;
                if (count[cur[0]] > 0) {
                    heap.add(new int[]{cur[0], count[cur[0]]});
                }
                while (!tmp.isEmpty()) {
                    heap.add(tmp.poll());
                }
            }
        }
        return sb.length() != n ? "" : sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "aab";
        String res = s.reorganizeString(str);
        System.out.println(res);
    }
}
