import java.util.*;


class Solution {
    public List<Integer> partitionLabels(String s) {
        List<Integer> ret = new ArrayList<>();
        char[] ch = s.toCharArray();
        int n = ch.length, i, lBound = 0, rBound = 0, idx;
        int[] last = new int[26];
        for (i = 0; i < n; i++) {
            last[ch[i] - 'a'] = i;
        }
        for (i = 0; i < n; i++) {
            idx = ch[i] - 'a';
            if (last[idx] > rBound) {
                rBound = last[idx];
            } else if (i == last[idx] && last[idx] == rBound) {
                ret.add(rBound - lBound + 1);
                lBound = i + 1;
                rBound = i + 1;
            }
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "ababcbacadefegdehijhklij";
        List<Integer> ret = s.partitionLabels(str);
        System.out.println(ret);
    }
}
