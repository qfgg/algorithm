import java.util.*;

class Solution {
    public String buildStr(List<Integer> tmp, int center, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((char)(tmp.get(i) + 'a'));
        }
        if (center != -1) {
            sb.append((char) (center + 'a'));
        }
        for (int i = n - 1; i >= 0; i--) {
            sb.append((char)(tmp.get(i) + 'a'));
        }
        return sb.toString();
    }
    public void dfs(int[] cnt, List<Integer> keys, int center, List<Integer> tmp, List<String> res, int num) {
        if (num == 0) {
            res.add(buildStr(tmp, center, tmp.size()));
            return;
        }
        int n = keys.size(), i, cur;
        for (i = 0; i < n; i++) {
            cur = keys.get(i);
            if (cnt[cur] > 0) {
                cnt[cur]--;
                tmp.add(cur);
                dfs(cnt, keys, center, tmp, res, num - 1);
                tmp.remove(tmp.size() - 1);
                cnt[cur]++;
            }
        }
    }
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length(), center = -1;
        int[] cnt = new int[26];
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (cnt[s.charAt(i) - 'a'] == 0) {
                keys.add(s.charAt(i) - 'a');
            }
            cnt[s.charAt(i) - 'a']++;
        }
        for (Integer ci : keys) {
            if (cnt[ci] % 2 == 1 && cnt[ci] != 1) {
                return res;
            }
            if (cnt[ci] == 1) {
                if (center != -1) {
                    return res;
                }
                cnt[ci] = 0;
                center = ci;
            } else {
                cnt[ci] = cnt[ci] / 2;
            }
        }
        List<Integer> tmp = new ArrayList<>();
        int total = n / 2;
        dfs(cnt, keys, center, tmp, res, total);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "abababacaba";
        List<String> res = s.generatePalindromes(str);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
