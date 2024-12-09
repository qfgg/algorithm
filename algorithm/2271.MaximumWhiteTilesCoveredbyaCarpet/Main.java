import java.util.*;

public class Main {
    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int max = 0, n = tiles.length, l = 0, r = 0, len = 0, realLen = 0;
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        List<int[]> w = new ArrayList<>();
        int[] left, right;
        for (int i = 0; i < n; i++) {
            w.add(new int[]{
                    tiles[i][1] - tiles[i][0] + 1,
                    i == n - 1 ? 0 : tiles[i + 1][0] - tiles[i][1] - 1
            });
        }
        while (r < n) {
            right = w.get(r);
            len += right[0] + right[1];
            realLen += right[0];
            if (len >= carpetLen) {
                if (len - right[1] > carpetLen) {
                    if (realLen - (len - right[1] - carpetLen) > max) {
                        max = realLen - (len - right[1] - carpetLen);
                    }
                } else {
                    if (realLen > max) {
                        max = realLen;
                    }
                }
                while (l <= r && len > carpetLen) {
                    left = w.get(l);
                    len -= left[0] + left[1];
                    realLen -= left[0];
                    if (len - right[1] > carpetLen) {
                        if (realLen - (len - right[1] - carpetLen) > max) {
                            max = realLen - (len - right[1] - carpetLen);
                        }
                    } else {
                        if (realLen > max) {
                            max = realLen;
                        }
                    }
                    l++;
                }
            }
            r++;
        }
        if (realLen > max) {
            max = realLen;
        }
        return max;
    }
    public static void main(String[] args) {
        int[][] tiles = new int[][]{
                {10, 14}, {16, 19}, {24, 32}, {41, 50}, {60, 68}
        };
        int carpetLen = 50;
        int res = maximumWhiteTiles(tiles, carpetLen);
        System.out.println(res);
    }
}
