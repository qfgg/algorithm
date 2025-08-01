import java.util.*;


class Solution {
    int attempt(List<Integer> candidates, List<Integer> path, int[][] match, int n) {
        if (candidates.size() <= 2) {
            return candidates.get(0);
        }
        List<Integer> group = candidates;
        int guess = -1;
        for (int i = 0; i < n; i++) {
            if (!path.contains(i)) {
                List<Integer>[] groups = new List[7];
                for (int j = 0; j < 7; j++) {
                    groups[j] = new ArrayList<>();
                }
                for (Integer k : candidates) {
                    if (k != i) {
                        groups[match[i][k]].add(k);
                    }
                }
                List<Integer> maxgroup = groups[0];
                for (int j = 0; j < 7; j++) {
                    if (groups[j].size() > maxgroup.size()) {
                        maxgroup = groups[j];
                    }
                }
                if (maxgroup.size() < group.size()) {
                    group = maxgroup;
                    guess = i;
                }
            }
        }
        return guess;
    }
    public void findSecretWord(String[] words, Master master) {
        int i, j, k, n = words.length, cnt, m;
        int[][] match = new int[n][n];
        for (i = 0 ; i < n; i++) {
            for (j = i ; j < n; j++) {
                cnt = 0;
                for (k = 0 ; k < 6; k++) {
                    if (words[i].charAt(k) == words[j].charAt(k)) {
                        cnt++;
                    }
                }
                match[i][j] = cnt;
                match[j][i] = cnt;
            }
        }
        List<Integer> candidates = new ArrayList<>();
        for (i = 0 ; i < n; i++) {
            candidates.add(i);
        }
        List<Integer> path = new ArrayList<>();
        while (!candidates.isEmpty()) {
            int guess = attempt(candidates, path, match, n);
            int num = master.guess(words[guess]);
            if (num == 6) {
                return;
            }
            m = candidates.size();
            List<Integer> next = new ArrayList<>();
            for (j = 0; j < m; j++) {
                if (match[guess][candidates.get(j)] == num) {
                    next.add(candidates.get(j));
                }
            }
            candidates = next;
            path.add(guess);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String secret = "acckzz";
        String[] words = new String[]{"acckzz","ccbazz","eiowzz","abcczz"};
        int res = s.findSecretWord(words, master);
        System.out.println(res);
    }
}
