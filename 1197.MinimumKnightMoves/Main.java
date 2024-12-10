import java.util.*;

class Solution {
//  hash x, y 300,300 as 30003003, in which last 3 means binary 11, 2 positive signs
    public int minKnightMoves(int x, int y) {
        int i, cur, curX, curY, key, size, moves = 0;
        int[] dir = new int[]{1,2,-1,-2,1,-2,-1,2,1};
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> vistied = new HashSet<>();
        q.add(3);
        size = q.size();
        while(size > 0) {
            cur = q.poll();
            vistied.add(cur);
            size--;
            curX = (cur / 10000 % 1000) * (((cur % 10) & 2) > 0 ? 1 : -1);
            curY = (cur % 10000 / 10 % 1000) * (((cur % 10) & 1) > 0 ? 1 : -1);
            if (curX == x && curY == y) {
                break;
            }
            for (i = 0; i < 8; i++) {
                key = (Math.abs(curX + dir[i]) * 1000 + Math.abs(curY + dir[i + 1])) * 10 + ((curX + dir[i] > 0 ? 2 : 0) + (curY + dir[i + 1] > 0 ? 1 : 0));
                if (!vistied.contains(key)) {
                    q.add(key);
                }
            }
            if (size == 0) {
                moves++;
                size = q.size();
            }
        }
        return moves;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.minKnightMoves(5, 5);
        System.out.println(res);
    }
}
