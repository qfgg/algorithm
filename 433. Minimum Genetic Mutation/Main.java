import java.util.*;


class Solution {
    List<String> replace(String str,HashSet<String> bankSet, HashSet<String> done) {
        List<String> res = new ArrayList<>();
        char[] gene = new char[]{'A','T','C','G'};
        char[] ch = str.toCharArray();
        char save;
        String cur;
        int n = ch.length, i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < 4; j++) {
                save = ch[i];
                if (gene[j] != save) {
                    ch[i] = gene[j];
                }
                cur = new String(ch);
                ch[i] = save;
                if (bankSet.contains(cur) && !done.contains(cur)) {
                    res.add(cur);
                    done.add(cur);
                }
            }
        }
        return res;
    }
    public int minMutation(String startGene, String endGene, String[] bank) {
        HashSet<String> bankSet = new HashSet<>();
        bankSet.addAll(Arrays.asList(bank));
        if (!bankSet.contains(endGene)) {
            return -1;
        }
        HashSet<String> done = new HashSet<>();
        String cur;
        List<String> nexts;
        Queue<String> q = new LinkedList<>();
        q.add(startGene);
        done.add(startGene);
        int size = q.size(), steps = 0;
        while(size != 0) {
            cur = q.poll();
            size--;
            nexts = replace(cur, bankSet, done);
            for (String next: nexts) {
                if (endGene.contains(next)) {
                    return steps + 1;
                }
                q.add(next);
            }
            if (size == 0) {
                steps++;
                size = q.size();
            }
        }
        return -1;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String startGene = "AACCTTGG", endGene = "AATTCCGG";
        String[] bank = {"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"};
        int res = s.minMutation(startGene, endGene, bank);
        System.out.println(res);
    }
}
