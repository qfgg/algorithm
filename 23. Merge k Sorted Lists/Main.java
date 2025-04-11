import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        if (lists.length == 0) {
            return ans;
        }
        ListNode[] l = new ListNode[lists.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> l[a].val - l[b].val);
        for (int i = 0; i < lists.length; i++) {
            l[i] = lists[i];
        }
        for (int i = 0; i < l.length; i++) {
            if (l[i] != null) {
                pq.add(i);
            }
        }
        int minIdx;
        ListNode min, cur = null;
        while (!pq.isEmpty()) {
            minIdx = pq.poll();
            min = l[minIdx];
            l[minIdx] = l[minIdx].next;
            if (l[minIdx] != null) {
                pq.add(minIdx);
            }
            min.next = null;
            if (ans == null) {
                ans = min;
                cur = ans;
            } else {
                cur.next = min;
                cur = cur.next;
            }
        }
        return ans;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode[] list = new ListNode[3];
        list[0] = new ListNode(1);
        list[0].next = new ListNode(4);
        list[0].next.next = new ListNode(5);
        list[1] = new ListNode(1);
        list[1].next = new ListNode(3);
        list[1].next.next = new ListNode(4);
        list[2] = new ListNode(2);
        list[2].next = new ListNode(6);
        ListNode res = s.mergeKLists(list);
        ListNode cur = res;
        while (cur != null) {
            System.out.print(cur.val);
            System.out.print(' ');
            cur = cur.next;
        }
    }
}
