import java.util.*;

public class Main {
    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        for (int[] tile : tiles) {
            if (tile[1] - tile[0] + 1 >= carpetLen) {
                return carpetLen;
            }
        }
        int max, n = tiles.length, l, r, end, lIdx = 0, rIdx = 0, cnt = 0;
        boolean isLIn = true, isRIn = true;
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        end = tiles[n - 1][1];
        l = tiles[0][0];
        r = l;
        while (r <= end && r - l + 1 <= carpetLen) {
            if (isRIn) {
                if (r >= tiles[rIdx][0] && r <= tiles[rIdx][1]) {
                    cnt++;
                } else {
                    isRIn = false;
                    rIdx++;
                    if (rIdx < n && r >= tiles[rIdx][0]) {
                        isRIn = true;
                        cnt++;
                    }
                }
            } else if (r >= tiles[rIdx][0]) {
                isRIn = true;
                cnt++;
            }
            r++;
        }
        max = cnt;
        while (r <= end) {
            if (isLIn) {
                if (l >= tiles[lIdx][0] && l <= tiles[lIdx][1]) {
                    cnt--;
                } else {
                    isLIn = false;
                    lIdx++;
                    if (lIdx < n && l >= tiles[lIdx][0]) {
                        isLIn = true;
                        cnt--;
                    }
                }
            } else if (l >= tiles[lIdx][0]) {
                isLIn = true;
                cnt--;
            }
            l++;
            if (isRIn) {
                if (r >= tiles[rIdx][0] && r <= tiles[rIdx][1]) {
                    cnt++;
                } else {
                    isRIn = false;
                    rIdx++;
                    if (rIdx < n && r >= tiles[rIdx][0]) {
                        isRIn = true;
                        cnt++;
                    }
                }
            } else if (r >= tiles[rIdx][0]) {
                isRIn = true;
                cnt++;
            }
            r++;
            if (cnt > max) {
                max = cnt;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[][] tiles = new int[][]{
                {1,1000000000}
        };
        int carpetLen = 1000000000;
        long res = maximumWhiteTiles(tiles, carpetLen);
        System.out.println(res);
    }
}
