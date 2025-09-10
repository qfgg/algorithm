import java.util.*;


class Solution {
    public int findMinArrowShots(int[][] points) {
        Map<Integer, List<Integer>> ins = new HashMap<>();
        Map<Integer, List<Integer>> outs = new HashMap<>();
        Set<Integer> done = new HashSet<>(), pts = new HashSet<>();
        List<Integer> idList;
        int n = points.length, i, x, cnt = 0;
        for (i = 0; i < n; i++) {
            if (ins.containsKey(points[i][0])) {
                idList = ins.get(points[i][0]);
            } else {
                idList = new ArrayList<>();
                ins.put(points[i][0], idList);
            }
            idList.add(i);
            if (outs.containsKey(points[i][1])) {
                idList = outs.get(points[i][1]);
            } else {
                idList = new ArrayList<>();
                outs.put(points[i][1], idList);
            }
            idList.add(i);
            pts.add(points[i][0]);
            pts.add(points[i][1]);
        }
        List<Integer> axis = new ArrayList<>(pts);
        Collections.sort(axis);
        pts.clear();
        n = axis.size();
        for (i = 0; i < n; i++) {
            x = axis.get(i);
            if (ins.containsKey(x)) {
                idList = ins.get(x);
                for (Integer id : idList) {
                    if (!done.contains(id)) {
                        pts.add(id);
                    }
                }
            }
            if (outs.containsKey(x)) {
                idList = outs.get(x);
                for (Integer id : idList) {
                    if (!done.contains(id)) {
                        cnt++;
                        done.addAll(pts);
                        pts.clear();
                        break;
                    }
                }
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] points = new int[][]{{1,2},{2,3},{3,4},{4,5}};
        int res = s.findMinArrowShots(points);
        System.out.println(res);
    }
}
