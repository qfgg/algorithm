import java.util.*;


public class Main {
    public static String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        int n = indices.length, sl = s.length(), i, j, srcl, pre = 0;
        int[] replaceIdxMap = new int[sl];
        Arrays.fill(replaceIdxMap, -1);
        for (i = 0; i < n; i++) {
            srcl = sources[i].length();
            for (j = 0; j < srcl; j++) {
                if (indices[i] + j > sl - 1 || s.charAt(indices[i] + j) != sources[i].charAt(j)) {
                    break;
                }
            }
            if (j == srcl) {
                replaceIdxMap[indices[i]] = i;
            }
        }
        for (i = 0; i < sl; i++) {
            if (replaceIdxMap[i] != -1) {
                sb.append(s.substring(pre, i));
                sb.append(targets[replaceIdxMap[i]]);
                pre = i + sources[replaceIdxMap[i]].length();
            }
        }
        if (pre == 0) {
            return s;
        }
        if (pre < sl) {
            sb.append(s.substring(pre, sl));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String s = "abcde";
        int[] indices = new int[]{2,2};
        String[] sources = new String[]{"cdef","bc"}, targets = new String[]{"f","fe"};
        String ans = findReplaceString(s, indices, sources, targets);
        System.out.println(ans);
    }
}
