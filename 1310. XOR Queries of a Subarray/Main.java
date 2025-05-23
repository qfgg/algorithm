import java.util.*;


class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length, i, qn = queries.length;
        int[] prexor = new int[n];
        prexor[0] = arr[0];
        for (i = 1; i < n; i++) {
            prexor[i] = prexor[i - 1] ^ arr[i];
        }
        int[] res = new int[qn];
        for (i = 0; i < qn; i++) {
            if (queries[i][0] == queries[i][1]) {
                res[i] = arr[queries[i][0]];
            } else if (queries[i][0] == 0) {
                res[i] = prexor[queries[i][1]];
            } else {
                res[i] = prexor[queries[i][1]] ^ prexor[queries[i][0] - 1];
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[]{1,3,4,8};
        int[][] queries = new int[][]{{0,1},{1,2},{0,3},{3,3}};
        int[] res = s.xorQueries(arr, queries);
        System.out.println(Arrays.toString(res));
    }
}
