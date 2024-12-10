import java.util.*;

public class Main {
    public static int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length, i, j, w, mh, candidate;
        int[] f = new int[n];
        f[0] = books[0][1];
        for (i = 1; i < n; i++) {
            candidate = Integer.MAX_VALUE;
            w = 0;
            mh = 0;
            j = i;
            while (j >= 0) {
                if (w + books[j][0] > shelfWidth) {
                    break;
                }
                w = w + books[j][0];
                if (books[j][1] > mh) {
                    mh = books[j][1];
                }
                candidate = Math.min(candidate, j > 0 ? f[j - 1] + mh : mh);
                j--;
            }
            f[i] = candidate;
        }
        return f[n - 1];
    }
    public static void main(String[] args) {
        int[][] books = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
        int shelfWidth = 4;
        int res = minHeightShelves(books, shelfWidth);
        System.out.println(res);
    }
}
