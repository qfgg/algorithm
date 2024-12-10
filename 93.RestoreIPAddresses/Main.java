import java.util.*;


class Main {

    public static void dfs(String s, int startIdx, int numOfParts, int leftLen, String[] ip, List<String> ans) {
        int min = numOfParts;
        int max = numOfParts * 3;
        if (leftLen < min || leftLen > max) {
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (startIdx + i > s.length() || (numOfParts == 1 && leftLen != i)) {
                continue;
            }
            String cur = s.substring(startIdx, startIdx + i);
            if ((cur.length() > 1 && cur.charAt(0) == '0') || Integer.parseInt(cur) > 255) {
                continue;
            }
            ip[4 - numOfParts] = cur;
            if (numOfParts == 1) {
                ans.add(String.join(".", ip));
                continue;
            }
            dfs(s, startIdx + i, numOfParts - 1, leftLen - i, ip, ans);
        }
    }
    public static List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        String[] ip = new String[4];
        dfs(s, 0, 4, s.length(), ip, ans);
        return ans;
    }

    public static void main(String[] args) {
        String s = "101023";
        List<String> ret = restoreIpAddresses(s);
        System.out.println(Arrays.toString(ret.toArray()));
    }
}
