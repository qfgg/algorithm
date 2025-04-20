import java.util.*;


class Solution {
    private List<int[]> mergeList(List<List<Integer>> nums) {
        List<int[]> merged = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b [0] ? a[1] - b [1] : a[0] - b[0]);
        int i, k = nums.size();
        int[] pos = new int[k], cur;
        for (i = 0; i < k; i++) {
            pq.add(new int[]{nums.get(i).getFirst(), i});
        }
        List<Integer> l;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            pos[cur[1]]++;
            l = nums.get(cur[1]);
            if (pos[cur[1]] < l.size()) {
                pq.add(new int[]{l.get(pos[cur[1]]), cur[1]});
            }
            merged.add(cur);
        }
        return merged;
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        List<int[]> merged = mergeList(nums);
        int len = merged.size(), l = 0, r = 0, k = nums.size(), n = 0, minWidth = Integer.MAX_VALUE, width;
        HashMap<Integer, Integer> count = new HashMap<>();
        int[] range = new int[2], cur, left;
        while (r < len) {
            cur = merged.get(r);
            if (count.getOrDefault(cur[1], 0) == 0) {
                n++;
                count.put(cur[1], 1);
            } else {
                count.put(cur[1], count.get(cur[1]) + 1);
            }
//          when all lists are included, left may have more than enough
            if (n == k) {
                left = merged.get(l);
                while (count.get(left[1]) > 1) {
                    count.put(left[1], count.get(left[1]) - 1);
                    l++;
                    left = merged.get(l);
                }
                width = merged.get(r)[0] - merged.get(l)[0];
                if (width < minWidth) {
                    minWidth = width;
                    range[0] = merged.get(l)[0];
                    range[1] = merged.get(r)[0];
                }
            }
            r++;
        }
        return range;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(new ArrayList<>(Arrays.asList(4,10,15,24,26)));
        nums.add(new ArrayList<>(Arrays.asList(0,9,12,20)));
        nums.add(new ArrayList<>(Arrays.asList(5,18,22,30)));
        int[] res = s.smallestRange(nums);
        System.out.println(Arrays.toString(res));
    }
}
