import java.util.*;


class Solution {
    String getNext(String str, int start, StringBuilder sb) {
        int n = str.length();
        if (start >= n) {
            return null;
        }
        int i = start;
        char ch = str.charAt(i);
        while (ch != '/') {
            sb.append(ch);
            i++;
            if (i == n) {
                break;
            }
            ch = str.charAt(i);
        }
        if (i < n) {
            ch = str.charAt(i);
            while (ch == '/') {
                sb.append(ch);
                i++;
                if (i == n) {
                    break;
                }
                ch = str.charAt(i);
            }
        }
        String res = sb.toString();
        sb.setLength(0);
        return res;
    }
    public String simplifyPath(String path) {
        Deque<String>  q = new ArrayDeque<>();
        StringBuilder nSb = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        int start = 0, n = path.length();;
        while (start < n && path.charAt(start) == '/') {
            start++;
        }
        q.push("/");
        String next = getNext(path, start, nSb);
        while (next != null) {
            start += next.length();
            next = next.replaceAll("/+$", "/");
            if (next.equals("../") || next.equals("..")) {
                if (q.size() > 1) {
                    q.pop();
                }
            } else if (!next.equals("./") && !next.equals(".")) {
                q.push(next);
            }
            next = getNext(path, start, nSb);
        }
        while (!q.isEmpty()) {
            next = q.removeLast();
            if (q.isEmpty() && next.endsWith("/") && !sb.isEmpty()) {
                sb.append(next.substring(0, next.length() - 1));
            } else {
                sb.append(next);
            }
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String path = "/.";
        String res = s.simplifyPath(path);
        System.out.println(res);
    }
}
