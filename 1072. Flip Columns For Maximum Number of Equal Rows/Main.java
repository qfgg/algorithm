import java.util.*;


class Solution {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int max = 0, cur;
        HashMap<String, Integer> patternCount = new HashMap<>();
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();
        String original, flip;
        for (int[] row : matrix) {
            for (int c : row) {
                s1.append(c);
                s2.append(c ^ 1);
            }
            original = s1.toString();
            flip = s2.toString();
            if (patternCount.containsKey(original)) {
                cur = patternCount.get(original) + 1;
                patternCount.put(original, cur);
            } else if (patternCount.containsKey(flip)) {
                cur = patternCount.get(flip) + 1;
                patternCount.put(flip, cur);
            } else {
                cur = 1;
                patternCount.put(original, cur);
            }
            max = Math.max(max, cur);
            s1.setLength(0);
            s2.setLength(0);
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][]{
                {0},
                {1},
                {0},
                {0},
                {1},
                {1},
                {1},
                {1},
                {0},
                {1},
        };
        int res = s.maxEqualRowsAfterFlips(matrix);
        System.out.println(res);
    }
}
