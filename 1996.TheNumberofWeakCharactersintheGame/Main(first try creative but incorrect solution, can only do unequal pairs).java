import java.util.*;


public class Main {
    public static int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, Comparator.comparingInt(a -> a[0]));
        int pl = properties.length, i, cnt = 0;
        Integer[] idx = new Integer[pl];
        for (i = 0; i < pl; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, Comparator.comparingInt(a -> properties[a][1]));
        int[] idxMap = new int[pl];
        for (i = 0; i < pl; i++) {
            idxMap[idx[i]] = i;
        }
        int leftEnd = 0;
        for (i = pl - 1; i >= 0; i--) {
            if (idxMap[i] > leftEnd) {
                cnt += idxMap[i] - leftEnd;
                leftEnd = idxMap[i] + 1;
            } else if (idxMap[i] == leftEnd) {
                leftEnd++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[][] properties = new int[][]{{1,1},{2,1},{2,2},{1,2}};
        int ans = numberOfWeakCharacters(properties);
        System.out.println(ans);
    }
}
