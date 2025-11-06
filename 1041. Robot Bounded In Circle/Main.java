import java.util.*;


class Solution {
    public boolean isRobotBounded(String instructions) {
        char[] ch = instructions.toCharArray();
        int n = ch.length, i, j;
        // north : 1, west: 2, south: 4, east: 8
        int x = 0, y = 0, curDir = 1, nextDir;
        Map<List<Integer>, Integer> dir = new HashMap<>();
        List<Integer> key;
        dir.put(new ArrayList<>(List.of(0, 0, 0)), 1);
        Map<Integer, int[]> dirMap = new HashMap<>();
        dirMap.put(1, new int[]{0, 1});
        dirMap.put(2, new int[]{-1, 0});
        dirMap.put(4, new int[]{0, -1});
        dirMap.put(8, new int[]{1, 0});
        int[] d;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < n; j++) {
                d = dirMap.get(curDir);
                if (ch[j] == 'G') {
                    x = x + d[0];
                    y = y + d[1];
                } else if (ch[j] == 'L') {
                    curDir = curDir == 8 ? 1 : (curDir << 1);
                } else if (ch[j] == 'R') {
                    curDir = curDir == 1 ? 8 : (curDir >> 1);
                }
                key = new ArrayList<>(List.of(x, y, (j + 1) % n));
                nextDir = 0;
                if (dir.containsKey(key)) {
                    nextDir = dir.get(key);
                    if ((nextDir & curDir) > 0) {
                        return true;
                    }
                }
                dir.put(key, (nextDir | curDir));
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String instructions = "GGLLGG";
        boolean res = s.isRobotBounded(instructions);
        System.out.println(res);
    }
}
