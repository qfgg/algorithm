import java.util.*;


class Solution {
    boolean removeTops(char[] s, char[] t, int sl, int tl, List<Integer> reversedIdx, List<int[]> span) {
        boolean canDo = false;
        int i, j, start = 0;
        for (i = 0; i <= tl - sl; i++) {
            for (j = 0; j < sl; j++) {
                if (t[i + j] != s[j]) {
                    break;
                }
            }
            if (j == sl) {
                canDo = true;
                reversedIdx.add(i);
                for (j = i; j < i + sl; j++) {
                    t[j] = '?';
                }
                span.add(new int[]{start, i - 1});
                start = i + sl;
            }
        }
        if (start < tl) {
            span.add(new int[]{start, tl - 1});
        }
        return canDo;
    }
    int match(char[] s, char[] t, int sl, int tl, int tstart, int sstart, int increment) {
        int matchCnt = 0;
        while (t[tstart] == s[sstart]) {
            matchCnt++;
            tstart = tstart + increment;
            sstart = sstart + increment;
            if (tstart < 0 || tstart == tl || sstart < 0 || sstart == sl || t[tstart] == '?' || t[tstart] == '#') {
                break;
            }
        }
        return matchCnt;
    }
    void mark(char[] t, int start, int end, char markChar) {
        for (int i = start; i <= end; i++) {
            t[i] = markChar;
        }
    }
    int isMid(char[] s, char[] t, int sl, int tleft, int tright) {
        int len = tright - tleft + 1;
        if (len >= sl) {
            return -1;
        }
        int i, j, start = -1;
        for (i = 1; i <= sl - len - 1; i++) {
            for (j = tleft; j <= tright; j++) {
                if (s[i + j - tleft] != t[j]) {
                    break;
                }
            }
            if (j == tright + 1) {
                start = tleft - i;
                break;
            }
        }
        return start;
    }
    boolean removeOne(int[] range, char[] s, char[] t, int sl, int tl, List<Integer> reversedIdx) {
        int i, j, maxMatchLen, matchedLen, start = 0, end = 0, left = range[0], right = range[1];
        i = range[0];
        while (i > 0 && i <= range[1]) {
            maxMatchLen = 0;
            for (j = sl - 1; j > 0; j--) {
                matchedLen = match(s, t, sl, tl, i, j, 1);
                if (matchedLen > maxMatchLen && matchedLen == sl - j) {
                    maxMatchLen = matchedLen;
                    start = i;
                    end = i + matchedLen - 1;
                }
            }
            if (maxMatchLen == 0) {
                break;
            }
            mark(t, start, end, '?');
            reversedIdx.add(end - sl + 1);
            i = end + 1;
            left = i;
        }
        i = range[1];
        while (i < tl - 1 && i >= range[0]) {
            maxMatchLen = 0;
            for (j = 0; j < sl - 1; j++) {
                matchedLen = match(s, t, sl, tl, i, j, -1);
                if (matchedLen > maxMatchLen && matchedLen == j + 1) {
                    maxMatchLen = matchedLen;
                    start = i - matchedLen + 1;
                    end = i;
                }
            }
            if (maxMatchLen == 0) {
                break;
            }
            mark(t, start, end, '?');
            reversedIdx.add(start);
            i = start - 1;
            right = i;
        }
        if (left <= right) {
            int idx = isMid(s, t, sl, left, right);
            if (idx < 0 || idx > tl - sl) {
                return false;
            }
            reversedIdx.add(idx);
        }
        return true;
    }
    public int[] movesToStamp(String stamp, String target) {
        char[] s = stamp.toCharArray(), t = target.toCharArray();
        int sl = s.length, tl = t.length;
        if (sl == tl) {
            if (stamp.equals(target)) {
                return new int[]{0};
            }
            return new int[]{};
        }
        boolean canRemove;
        List<Integer> reversedIdx = new ArrayList<>();
        List<int[]> span = new ArrayList<>();
        canRemove = removeTops(s, t, sl, tl, reversedIdx, span);
        if (!canRemove) {
            return new int[]{};
        }
        int spanSize = span.size(), i;
        for (i = 0; i < spanSize; i++) {
            canRemove = removeOne(span.get(i), s, t, sl, tl, reversedIdx);
            if (!canRemove) {
                return new int[]{};
            }
        }
        int n = reversedIdx.size();
        if (n > tl * 10) {
            return new int[]{};
        }
        int[] res = new int[n];
        for (i = 0; i < n; i++) {
            res[i] = reversedIdx.get(n - 1 - i);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String stamp = "bedaefaeddccbce", target = "bebedabebbedaefaeddccbced";
        int[] res = s.movesToStamp(stamp, target);
        System.out.println(Arrays.toString(res));
    }
}
