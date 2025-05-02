import java.util.*;


class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        boolean[] timeline = new boolean[100001];
        int next = 0;
        HashMap<Integer, Integer> inMap = new HashMap<>();
        HashMap<Integer, List<Integer>> outMap = new HashMap<>();
        HashMap<Integer, Integer> chairMap = new HashMap<>();
        PriorityQueue<Integer> returnedChairs = new PriorityQueue<>();
        int i, n = times.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (i = 0; i < n; i++) {
            timeline[times[i][0]] = true;
            timeline[times[i][1]] = true;
            inMap.put(times[i][0], i);
            if (!outMap.containsKey(times[i][1])) {
                outMap.put(times[i][1], new ArrayList<>(List.of(i)));
            } else {
                outMap.get(times[i][1]).add(i);
            }
            if (times[i][0] < min) {
                min = times[i][0];
            }
            if (times[i][1] > max) {
                max = times[i][1];
            }
        }
        List<Integer> outs;
        int in, firstAvailable = -1;
        for (i = min; i <= max; i++) {
            if (timeline[i]) {
                if (outMap.containsKey(i)) {
                    outs = outMap.get(i);
                    for (int out : outs) {
                        returnedChairs.add(chairMap.get(out));
                    }
                }
                if (inMap.containsKey(i)) {
                    in = inMap.get(i);
                    if (returnedChairs.isEmpty()) {
                        chairMap.put(in, next);
                        firstAvailable = next;
                        next++;
                    } else {
                        firstAvailable = returnedChairs.poll();
                        chairMap.put(in, firstAvailable);
                    }
                    if (in == targetFriend) {
                        break;
                    }
                }
            }
        }
        return firstAvailable;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] times = new int[][]{
                {3,10},
                {1,5},
                {2,6},
        };
        int targetFriend = 0;
        int res = s.smallestChair(times, targetFriend);
        System.out.println(res);
    }
}
