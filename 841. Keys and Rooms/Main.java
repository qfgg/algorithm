import java.util.*;


class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        List<Integer> room;
        int cur, i, n = rooms.size(), m, cnt = 1, next;
        boolean[] done = new boolean[n];
        done[0] = true;
        while (!q.isEmpty()) {
            cur = q.poll();
            room = rooms.get(cur);
            m = room.size();
            for (i = 0; i < m; i++) {
                next = room.get(i);
                if (done[next]) {
                    continue;
                }
                q.add(next);
                done[next] = true;
                cnt++;
            }
        }
        return cnt == n;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(new ArrayList<>(List.of(1, 3)));
        rooms.add(new ArrayList<>(List.of(3, 0, 1)));
        rooms.add(new ArrayList<>(List.of(2)));
        rooms.add(new ArrayList<>(List.of(0)));
        boolean ret = slt.canVisitAllRooms(rooms);
        System.out.println(ret);
    }
}
