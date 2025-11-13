import java.util.*;


class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        final int[] ln = nums1, sn = nums2;
        int n1 = ln.length, n2 = sn.length, i;
        int[] headIdx = new int[n2], top;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> ln[a[0]] + sn[a[1]] - ln[b[0]] - sn[b[1]]);
        for (i = 0; i < n2; i++) {
            q.add(new int[]{headIdx[i], i});
        }
        List<List<Integer>> res = new ArrayList<>();
        while (k > 0) {
            top = q.poll();
            res.add(new ArrayList<>(List.of(ln[top[0]], sn[top[1]])));
            if (headIdx[top[1]] < n1 - 1) {
                headIdx[top[1]]++;
                q.add(new int[]{headIdx[top[1]], top[1]});
            }
            k--;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums1 = new int[]{1,7,11}, nums2 = new int[]{2,4,6};
        int k = 3;
        List<List<Integer>> res = s.kSmallestPairs(nums1, nums2, k);
        System.out.println(res);
    }
}
