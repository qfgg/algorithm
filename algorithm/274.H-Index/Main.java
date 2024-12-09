import java.util.*;

public class Main {
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length, l = 0, r = n, m = 0;
        while (l < r) {
            m = l + ((r - l) >> 1);
            if (citations[m] == n - m) {
                return citations[m];
            }
            if (citations[m] < n - m) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return n - l;
    }
    public static void main(String[] args) {
        int[] citations = new int[]{3,0,6,1,5};
        int res = hIndex(citations);
        System.out.println(res);
    }
}
