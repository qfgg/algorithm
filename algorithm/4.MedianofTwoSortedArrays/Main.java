import java.util.*;


public class Main {
    public static boolean find(int[] ns1, int[] ns2, int n1, int n2, int ln, boolean even, double[] res) {
        if (n1 == 0) {
            return false;
        }
        int l = 0, r = Math.min(ns1.length, ln + 1), m, ln2;
        while (l < r) {
            m = l + ((r - l) >> 1);
            ln2 = ln - m;
            if (ln2 == n2) {
                if (n2 == 0 || ns1[m] >= ns2[n2 - 1]) {
                    res[0] = ns1[m];
                    if (even) {
                        res[1] = ns1[m + 1];
                    }
                    return true;
                }
                l = m + 1;
            } else if (ln2 > n2) {
                l = m + 1;
            } else {
                if ((ln2 == 0 || ns2[ln2 - 1] <= ns1[m]) && ns1[m] <= ns2[ln2]) {
                    res[0] = ns1[m];
                    if (even) {
                        res[1] = m + 1 >= n1 ? ns2[ln2] : Math.min(ns1[m + 1], ns2[ln2]);
                    }
                    return true;
                } else if (ln2 > 0 && ns1[m] < ns2[ln2 - 1]) {
                    l = m + 1;
                } else if (ns1[m] > ns2[ln2]) {
                    r = m;
                }
            }
        }
        return false;
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length, ln;
        boolean even = (n1 + n2) % 2 == 0;
        ln = even ? (n1 + n2) / 2 - 1 : (n1 + n2) / 2;
        double[] res = new double[2];
        if (find(nums1, nums2, n1, n2, ln, even, res)) {
            return even ? (double)(res[0] + res[1]) / 2 : res[0];
        }
        find(nums2, nums1, n2, n1, ln, even, res);
        return even ? (double)(res[0] + res[1]) / 2 : res[0];
    }
    public static void main(String[] args) {
        int[] nums1 = new int[]{0}, nums2 = new int[]{-1,0,1};
        double ans = findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }
}
