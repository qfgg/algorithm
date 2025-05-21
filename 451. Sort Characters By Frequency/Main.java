import java.util.*;


class Solution {
    private int getIdx(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 26;
        }
        return c - '0' + 52;
    }
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        int[] count = new int[62];
        List<Character> cl = new ArrayList<>();
        int i, j;
        for (char c : chars) {
            i = getIdx(c);
            if (count[i] == 0) {
                cl.add(c);
            }
            count[i]++;
        }
        Collections.sort(cl, (a, b) -> count[getIdx(a)] == count[getIdx(b)] ? a - b : count[getIdx(b)] - count[getIdx(a)]);
        StringBuilder sb = new StringBuilder();
        for (Character ch : cl) {
            j = count[getIdx(ch)];
            for (i = 0; i < j; i++) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "nkwongwow";
        String res = s.frequencySort(str);
        System.out.println(res);
    }
}
