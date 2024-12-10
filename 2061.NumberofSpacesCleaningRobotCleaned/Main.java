import java.util.*;


public class Main {
    public static int numberOfCleanRooms(int[][] room) {
        int[] dir = new int[]{0,1,0,-1,0};
        int w = room[0].length, h = room.length;
        int[][] seen = new int[h][w];
        int x = 0, y = 0, num = 1, nX, nY, cur = 0;
        room[0][0] = -1;
        seen[0][0] |= 1 << cur;
        while (true) {
            nX = x + dir[cur + 1];
            nY = y + dir[cur];
            if (nX == w || nY == h || nX < 0 || nY < 0 || room[nY][nX] == 1) {
                cur = (cur + 1) % 4;
            } else {
                x = nX;
                y = nY;
                if (room[y][x] == 0) {
                    room[y][x] = -1;
                    num++;
                }
            }
            if (((seen[y][x] >> cur) & 1) == 1) {
                return num;
            }
            seen[y][x] |= 1 << cur;
        }
    }
    public static void main(String[] args) {
        int[][] room = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        int ans = numberOfCleanRooms(room);
        System.out.println(ans);
    }
}
