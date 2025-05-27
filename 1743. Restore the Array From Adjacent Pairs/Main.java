import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode pre;
    ListNode() {}
    ListNode(int val) { this.val = val; }
}
class Solution {
    private ListNode append(HashMap<Integer, Integer> m, int key, ListNode headOrTail, boolean toFront, int[] count) {
        int next = m.get(key);
        ListNode ret = new ListNode(next);
        count[0]++;
        m.remove(key);
        if (toFront) {
            headOrTail.pre = ret;
            headOrTail.pre.next = headOrTail;
        } else {
            headOrTail.next = ret;
            headOrTail.next.pre = headOrTail;
        }
        return ret;
    }
    public int[] restoreArray(int[][] adjacentPairs) {
        HashMap<Integer, Integer> m1 = new HashMap<>();
        HashMap<Integer, Integer> m2 = new HashMap<>();
        int i, n = adjacentPairs.length;
        int[] count = new int[1];
        for (i = 1; i < n; i++) {
            if (!m1.containsKey(adjacentPairs[i][0])) {
                m1.put(adjacentPairs[i][0], adjacentPairs[i][1]);
            } else {
                m2.put(adjacentPairs[i][0], adjacentPairs[i][1]);
            }
            if (!m1.containsKey(adjacentPairs[i][1])) {
                m1.put(adjacentPairs[i][1], adjacentPairs[i][0]);
            } else {
                m2.put(adjacentPairs[i][1], adjacentPairs[i][0]);
            }
        }
        ListNode head = new ListNode(adjacentPairs[0][0]);
        ListNode tail = new ListNode(adjacentPairs[0][1]);
        head.next = tail;
        tail.pre = head;
        count[0] = 2;
        int l = adjacentPairs[0][0], r = adjacentPairs[0][1];
        while (true) {
            if (m1.containsKey(l) && !m1.get(l).equals(head.next.val)) {
                head = append(m1, l, head, true, count);
            } else if (m2.containsKey(l) && !m2.get(l).equals(head.next.val)) {
                head = append(m2, l, head, true, count);
            } else {
                break;
            }
            l = head.val;
        }
        while (true) {
            if (m1.containsKey(r) && !m1.get(r).equals(tail.pre.val)) {
                tail = append(m1, r, tail, false, count);
            } else if (m2.containsKey(r) && !m2.get(r).equals(tail.pre.val)) {
                tail = append(m2, r, tail, false, count);
            } else {
                break;
            }
            r = tail.val;
        }
        int[] res = new int[count[0]];
        i = 0;
        while (head != null) {
            res[i] = head.val;
            head = head.next;
            i++;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] adjacentPairs = new int[][]{
                {4,-2},
                {1,4},
                {-3,1},
        };
        int[] res = s.restoreArray(adjacentPairs);
        System.out.println(Arrays.toString(res));
    }
}
