import java.util.*;


class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<Integer> pre1 = new HashSet<>();
        HashSet<Integer> pre2 = new HashSet<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int i, n1 = arr1.length, n2 = arr2.length, cur, longest = 0;
        for (i = n1 - 1; i >= 0; i--) {
            cur = arr1[i];
            if (!pre1.contains(cur)) {
                while (cur > 0) {
                    pre1.add(cur);
                    q.add(new int[]{cur, 0});
                    cur = cur / 10;
                }
            }
        }
        for (i = n2 - 1; i >= 0; i--) {
            cur = arr2[i];
            if (!pre2.contains(cur)) {
                while (cur > 0) {
                    pre2.add(cur);
                    q.add(new int[]{cur, 1});
                    cur = cur / 10;
                }
            }
        }
        int[] top;
        while (!q.isEmpty()) {
            top = q.poll();
            if ((top[1] == 0 && pre2.contains(top[0])) ||
                    (top[1] == 1 && pre1.contains(top[0]))) {
                longest = top[0];
                break;
            }
        }
        int count = 0;
        while (longest > 0) {
            longest = longest / 10;
            count++;
        }
        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr1 = new int[]{13,27,45}, arr2 = new int[]{21,27,48};
        int res = s.longestCommonPrefix(arr1, arr2);
        System.out.println(res);
    }
}
