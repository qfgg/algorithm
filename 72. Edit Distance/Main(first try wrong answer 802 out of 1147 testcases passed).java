import java.util.*;


class Solution {
    public int minDistance(String word1, String word2) {
        String l = word1, s = word2;
        if (word1.length() < word2.length()) {
            l = word2;
            s = word1;
        }
        char[] lc = l.toCharArray(), sc = s.toCharArray();
        int ln = l.length(), sn = s.length(), i, j, extra = (sn - 1) / 2, min = ln, curMatch, last, adds;
        List<Integer>[][] matched = new List[sn][ln];
        List<Integer> c1, c2;
        for (i = 0; i < sn; i++) {
            j = 0;
            curMatch = -1;
            while (j < ln) {
                if (i - j > extra) {
                    j++;
                    continue;
                }
                if (sn - i - (ln - j) > extra) {
                    break;
                }
                c1 = i > 0 && j > 0 && matched[i - 1][j - 1] != null ? new ArrayList<>(matched[i - 1][j - 1]) : null;
                c2 = j > 0 && matched[i][j - 1] != null ? new ArrayList<>(matched[i][j - 1]) : null;
                adds = 0;
                if (sc[i] == lc[j]) {
                    if (c1 == null) {
                        if (curMatch == -1) {
                            if (c2 != null) {
                                c2.add(j);
                            }
                            matched[i][j] = c2 == null ? new ArrayList<>(List.of(j)) : c2;
                            curMatch = j;
                        }
                    } else {
                        last = c1.getLast();
                        if (j <= last) {
                            matched[i][j] = c1;
                            curMatch = -1;
                        } else {
                            matched[i][j] = c1;
                            matched[i][j].add(j);
                            curMatch = j;
                        }
                    }
                } else {
                    matched[i][j] = c1 != null ? c1 : c2;
//                  // "sea" -> "ate"
                    if (curMatch != -1 && ln - (j - curMatch) < sn) {
                        adds += sn - ln + j - curMatch;
                    }
                }
                if (i - j <= extra && i > j) {
                    adds += i - j;
                }
                if (sn - i - (ln - j) <= extra && sn - i > ln -j) {
                    adds += sn - i - (ln - j);
                }
                min = Math.min(min, ln - (matched[i][j] == null ? 0 : matched[i][j].size()) + adds);
                j++;
            }
        }
        return min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String word1 = "prosperity", word2 = "properties";
        int res = s.minDistance(word1, word2);
        System.out.println(res);
    }
}
