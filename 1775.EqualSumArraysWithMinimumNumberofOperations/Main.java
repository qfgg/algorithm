import java.util.*;


public class Main {
    public static int minOperations(int[] nums1, int[] nums2) {
        int s1 = 0, s2 = 0, diff, cnt = 0;
        int[] l = nums1, s = nums2;
        for (int n : nums1) {
            s1 += n;
        }
        for (int n : nums2) {
            s2 += n;
        }
        diff = s1 - s2;
        if (diff == 0) {
            return 0;
        }
        if (diff < 0) {
            l = nums2;
            s = nums1;
            diff = -1 * diff;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int n : l) {
            pq.add(n - 1);
        }
        for (int n : s) {
            pq.add(6 - n);
        }
        while (!pq.isEmpty() && diff > 0) {
            diff = diff - pq.poll();
            cnt++;
        }
        return diff > 0 ? -1 : cnt;
    }
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,4,5,6};
        int[] nums2 = new int[]{1,1,2,2,2,2};
        int ans = minOperations(nums1, nums2);
        System.out.println(ans);
    }
}
