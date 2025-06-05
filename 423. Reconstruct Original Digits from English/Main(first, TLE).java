import java.util.*;


class Solution {
    void dfs(int[][] numberCount, int[] idx, int cur, int[] count, char[] dict, int[] digits, int[] res) {
        int i, j, n = dict.length, letterIdx;
        if (cur == 7) {
            for (i = 0; i < n; i++) {
                letterIdx = dict[i] - 'a';
                if (count[letterIdx] > 0) {
                    return;
                }
            }
            for (i = 0; i < 10; i++) {
                res[i] = digits[i];
            }
            return;
        }
        int curCount = Integer.MAX_VALUE;
        for (i = 0; i < n; i++) {
            letterIdx = dict[i] - 'a';
            if (numberCount[cur][letterIdx] > 0) {
                if (count[letterIdx] >= numberCount[cur][letterIdx]) {
                    curCount = Math.min(curCount, count[letterIdx] / numberCount[cur][letterIdx]);
                } else {
                    curCount = Integer.MAX_VALUE;
                    break;
                }
            }
        }
        int[] copy = new int[26];
        if (curCount != Integer.MAX_VALUE && curCount > 0) {
            for (i = curCount; i > 0; i--) {
                digits[idx[cur]] = i;
                for (j = 0; j < n; j++) {
                    letterIdx = dict[j] - 'a';
                    copy[letterIdx] = count[letterIdx];
                }
                for (j = 0; j < n; j++) {
                    letterIdx = dict[j] - 'a';
                    if (numberCount[cur][letterIdx] > 0) {
                        count[letterIdx] -= i * numberCount[cur][letterIdx];
                    }
                }
                dfs(numberCount, idx, cur + 1, count, dict, digits, res);
                for (j = 0; j < n; j++) {
                    letterIdx = dict[j] - 'a';
                    count[letterIdx] = copy[letterIdx];
                }
                digits[idx[cur]] = 0;
            }
        }
        dfs(numberCount, idx, cur + 1, count, dict, digits, res);
    }
    public String originalDigits(String s) {
        int[] idx = new int[]{1,3,4,5,7,8,9};
        char[][] numbers = new char[][]{
                {'o','n','e'},
                {'t','h','r','e','e'},
                {'f','o','u','r'},
                {'f','i','v','e'},
                {'s','e','v','e','n'},
                {'e','i','g','h','t'},
                {'n','i','n','e'}
        };
        int[][] numberCount = new int[7][26];
        int[] digits = new int[10];
        int[] res = new int[10];
        int[] count = new int[26];
        char[] c = s.toCharArray();
        int n = c.length, i, j, num;
        for (i = 0; i < n ; i++) {
            count[c[i] - 'a']++;
        }
        if (count['z' - 'a'] > 0) {
            num = count['z' - 'a'];
            count['z' - 'a'] -= num;
            count['e' - 'a'] -= num;
            count['r' - 'a'] -= num;
            count['o' - 'a'] -= num;
            digits[0] = num;
        }
        if (count['w' - 'a'] > 0) {
            num = count['w' - 'a'];
            count['t' - 'a'] -= num;
            count['w' - 'a'] -= num;
            count['o' - 'a'] -= num;
            digits[2] = num;
        }
        if (count['x' - 'a'] > 0) {
            num = count['x' - 'a'];
            count['s' - 'a'] -= num;
            count['i' - 'a'] -= num;
            count['x' - 'a'] -= num;
            digits[6] = num;
        }
        for (i = 0; i < 7; i++) {
            for (j = 0; j < numbers[i].length; j++) {
                numberCount[i][numbers[i][j] - 'a']++;
            }
        }
        char[] dict = new char[]{'e','g','f','i','h','o','n','s','r','u','t','w','v','x','z'};
        dfs(numberCount, idx, 0, count, dict, digits, res);
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < 10; i++) {
            if (res[i] > 0) {
                sb.append(String.valueOf((char)(i + '0')).repeat(res[i]));
            }
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "ereht";
        String res = s.originalDigits(str);
        System.out.println(res);
    }
}
