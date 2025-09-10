import java.util.*;


class Solution {
    int findLeft(int target, List<Integer> points) {
        int l = 0, r = points.size(), m, cur, left = -1;
        while (l < r) {
            m = (l + r) / 2;
            cur = points.get(m);
            if (cur == target) {
                return m;
            }
            if (cur < target) {
                left = Math.max(left, m);
                l = m + 1;
            } else {
                r = m;
            }
        }
        return left;
    }
    void addInterval(int[] interval, List<Integer> mergedStarts, Map<Integer, Integer> mergedEnds, Map<Integer, Integer> minMap, Map<Integer, Integer> ends) {
        int start = interval[0], end = interval[1], width = end - start + 1;
        int leftIdx, startIdx, curStart, curEnd, nextStart, rmStartIdx = -1, rmEndIdx = -1, overlapSIdx = -1, overLapEIdx = -1, len, ms, me;
        leftIdx = findLeft(start, mergedStarts);
        startIdx = leftIdx;
        if (startIdx != -1 && start >= mergedStarts.get(startIdx)) {
            curEnd = mergedEnds.get(mergedStarts.get(startIdx));
            if (end <= curEnd) {
                return;
            }
            if (start <= curEnd) {
                overlapSIdx = startIdx;
            }
        }
        len = mergedStarts.size();
        startIdx++;
        while (startIdx < len) {
            curStart = mergedStarts.get(startIdx);
            curEnd = mergedEnds.get(curStart);
            if (curEnd > end) {
                if (curStart <= end) {
                    overLapEIdx = startIdx;
                }
                break;
            }
            if (rmStartIdx == -1) {
                rmStartIdx = startIdx;
            }
            rmEndIdx = startIdx;
            startIdx++;
        }
        if (overlapSIdx == -1) {
            ms = start;
            curStart = start;
        } else {
            ms = mergedStarts.get(overlapSIdx);
            curStart = mergedEnds.get(ms);
            mergedEnds.remove(ms);
        }
        if (rmStartIdx != -1) {
            startIdx = rmStartIdx;
            while (startIdx <= rmEndIdx) {
                nextStart = mergedStarts.get(startIdx);
                ends.put(curStart, nextStart);
                minMap.put(curStart, width);
                curStart = mergedEnds.get(nextStart);
                mergedEnds.remove(nextStart);
                startIdx++;
            }
        }
        if (overLapEIdx == -1) {
            me = end;
            ends.put(curStart, me);
            minMap.put(curStart, width);
        } else {
            nextStart = mergedStarts.get(overLapEIdx);
            me = mergedEnds.get(nextStart);
            mergedEnds.remove(nextStart);
            ends.put(curStart, nextStart);
            minMap.put(curStart, width);
        }
        mergedEnds.put(ms, me);
        if (rmStartIdx == -1 && overLapEIdx == -1) {
            if (leftIdx == -1 || start > mergedEnds.get(mergedStarts.get(leftIdx))) {
                mergedStarts.add(leftIdx + 1, start);
            }
        } else if (rmStartIdx == - 1) {
            if (leftIdx == -1 || start > mergedEnds.get(mergedStarts.get(leftIdx))) {
                mergedStarts.set(overLapEIdx, start);
            } else {
                mergedStarts.remove(overLapEIdx);
            }
        } else if (overLapEIdx == -1) {
            if (leftIdx == -1 || start > mergedEnds.get(mergedStarts.get(leftIdx))) {
                mergedStarts.set(rmStartIdx, start);
                mergedStarts.subList(rmStartIdx + 1, rmEndIdx + 1).clear();
            } else {
                mergedStarts.subList(rmStartIdx, rmEndIdx + 1).clear();
            }
        } else {
            if (leftIdx == -1 || start > mergedEnds.get(mergedStarts.get(leftIdx))) {
                mergedStarts.set(rmStartIdx, start);
                mergedStarts.subList(rmStartIdx + 1, overLapEIdx + 1).clear();
            } else {
                mergedStarts.subList(rmStartIdx, overLapEIdx + 1).clear();
            }
        }
    }
    int search(int query, List<Integer> starts, Map<Integer, Integer> ends, Map<Integer, Integer> minMap, Set<Integer> points) {
        if (points.contains(query)) {
            return 1;
        }
        int left = findLeft(query, starts);
        if (left == -1 || query > ends.get(starts.get(left))) {
            return -1;
        }
        int size = minMap.get(starts.get(left)), leftStart;
        if (left > 0) {
            leftStart = starts.get(left - 1);
            if (query == ends.get(leftStart)) {
                size = Math.min(size, minMap.get(leftStart));
            }
        }
        return size;
    }
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = queries.length, i;
        int[] res = new int[n];
        Set<Integer> points = new HashSet<>();
        Map<Integer, Integer> minMap = new HashMap<>();
        List<Integer> mergedStarts = new ArrayList<>();
        Map<Integer, Integer> mergedEnds = new HashMap<>();
        Map<Integer, Integer> ends = new HashMap<>();
        Arrays.sort(intervals, (a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        n = intervals.length;
        for (i = 0; i < n; i++) {
            if (i > 0 && intervals[i][0] == intervals[i - 1][0] && intervals[i][1] == intervals[i - 1][1]) {
                continue;
            }
            if (intervals[i][0] == intervals[i][1]) {
                points.add(intervals[i][0]);
            } else {
                addInterval(intervals[i], mergedStarts, mergedEnds, minMap, ends);
            }
        }
        List<Integer> starts = new ArrayList<>(minMap.keySet());
        Collections.sort(starts);
        n = queries.length;
        for (i = 0; i < n; i++) {
            res[i] = search(queries[i], starts, ends, minMap, points);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals = new int[][]{
                {1, 5}, {3, 8}, {1, 6}, {1, 9}
        };
        int[] queries = new int[]{7};
        int[] res = s.minInterval(intervals, queries);
        System.out.println(Arrays.toString(res));
    }
}
