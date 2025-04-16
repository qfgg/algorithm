import java.util.*;

class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        PriorityQueue<int[]>[] priorityLists = new PriorityQueue[100001];
        Set<Integer> lefts = new HashSet<>();
        int i, ql = queries.length, used = 0, len = nums.length, l = 0;
        while (l < len && nums[l] == 0) {
            l++;
        }
        if (l == len) {
            return queries.length;
        }
        for (i = 0; i < ql; i++) {
            if (priorityLists[queries[i][0]] == null) {
                priorityLists[queries[i][0]] = new PriorityQueue<>((a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
            }
            priorityLists[queries[i][0]].add(queries[i]);
            lefts.add(queries[i][0]);
        }
        List<Integer> ls = new ArrayList<>(lefts);
        Collections.sort(ls);
        int[] diff = new int[len + 1], curQuery;
        int plIdx, lsIdx, maxPlIdx, max, cur = 0;
        while (true) {
            max = -1;
            maxPlIdx = -1;
            lsIdx = 0;
            while (lsIdx < ls.size()) {
                plIdx = ls.get(lsIdx);
                if (priorityLists[plIdx].isEmpty()) {
                    lsIdx++;
                    continue;
                }
                curQuery = priorityLists[plIdx].peek();
                if (curQuery[0] <= l && curQuery[1] - l > max) {
                    max = curQuery[1] - l;
                    maxPlIdx = plIdx;
                } else if (curQuery[0] > l) {
                    break;
                }
                lsIdx++;
            }
            if (maxPlIdx == -1) {
                return -1;
            }
            curQuery = priorityLists[maxPlIdx].poll();
            used++;
            diff[l]++;
            diff[curQuery[1] + 1]--;
            while (l < len && cur + diff[l] >= nums[l]) {
                cur += diff[l];
                l++;
            }
            if (l == len) {
                break;
            }
        }
        return queries.length - used;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,0,2};
        int[][] queries = new int[][]{{0,2},{0,2},{1,1}};
        int res = s.maxRemoval(nums, queries);
        System.out.println(res);
    }
}
