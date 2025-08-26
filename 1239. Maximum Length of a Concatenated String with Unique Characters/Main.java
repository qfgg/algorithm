import java.util.*;


class Solution {
    boolean isValid(String s) {
        boolean[] visited = new boolean[26];
        int len = s.length(), i, idx;
        for (i = 0; i < len; i++) {
            idx = s.charAt(i) - 'a';
            if (visited[idx]) {
                return false;
            }
            visited[idx] = true;
        }
        return true;
    }
    boolean isListValid(List<String> arr) {
        boolean[] visited = new boolean[26];
        int n = arr.size(), len, i, j, idx;
        String cur;
        for (i = 0; i < n; i++) {
            cur = arr.get(i);
            len = cur.length();
            for (j = 0; j < len; j++) {
                idx = cur.charAt(j) - 'a';
                if (visited[idx]) {
                    return false;
                }
                visited[idx] = true;
            }
        }
        return true;
    }
    public int maxLength(List<String> arr) {
        List<String> validList = new ArrayList<>(), cur = new ArrayList<>();
        for (String s : arr) {
            if (isValid(s)) {
                validList.add(s);
            }
        }
        int n = validList.size(), bits = (1 << n) - 1, maxlen = 0, tmp, j, curTotal;
        String curStr;
        while (bits >= 0) {
            tmp = bits;
            j = 0;
            curTotal = 0;
            while (j < n) {
                if ((tmp & 1) == 1) {
                    curStr = validList.get(j);
                    cur.add(curStr);
                    curTotal += curStr.length();
                }
                tmp = tmp >> 1;
                j++;
            }
            if (curTotal > maxlen && isListValid(cur)) {
                maxlen = curTotal;
            }
            if (maxlen == 26) {
                break;
            }
            cur.clear();
            bits--;
        }
        return maxlen;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> arr = new ArrayList<>(List.of("un","iq","ue"));
        int res = s.maxLength(arr);
        System.out.println(res);
    }
}
