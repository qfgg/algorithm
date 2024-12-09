import java.util.*;


public class Main {
    public static int findHighers(List<Integer> higherDiff, int negDiff) {
        int l = 0, r = higherDiff.size(), m, end = r - 1, tmp;
        while (l < r) {
            m = l + ((r - l) >> 1);
            tmp = higherDiff.get(m);
            if (negDiff < tmp) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (l > end) {
            return 0;
        }
        if (negDiff < higherDiff.get(l)) {
            return end - l + 1;
        }
        return end - l;
    }
    public static long countPairs(int[] nums1, int[] nums2) {
        List<Integer> higherDiff = new ArrayList<>();
        List<Integer> lowerDiff = new ArrayList<>();
        int i, n = nums1.length, m;
        long cnt;
        for (i = 0; i < n; i++) {
            if (nums1[i] > nums2[i]) {
                higherDiff.add(nums1[i] - nums2[i]);
            } else {
                lowerDiff.add(nums2[i] - nums1[i]);
            }
        }
        Collections.sort(higherDiff);
        m = higherDiff.size();
        n = lowerDiff.size();
        if (m == 0) {
            return 0;
        }
        cnt = (long)m * (m - 1) / 2;
        for (i = 0; i < n; i++) {
            if (lowerDiff.get(i) == 0) {
                cnt += m;
            } else {
                cnt += findHighers(higherDiff, lowerDiff.get(i));
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[] nums1 = new int[1000];
        int[] nums2 = new int[1000];
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            nums1[i] = r.nextInt(10) + 1;
            nums2[i] = r.nextInt(10) + 1;
        }
        long res = countPairs(nums1, nums2);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(res);
    }
}
