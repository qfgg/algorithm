import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    private ListNode reverseFront(ListNode head, int count) {
        ListNode cur, next, nextnext;
        int i = 0;
        cur = head;
        next = cur.next;
        while (i < count) {
            nextnext = next.next;
            next.next = cur;
            cur = next;
            next = nextnext;
            i++;
        }
        head.next = next;
        return cur;
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null || left == right) {
            return head;
        }
        if (left == 1) {
            return reverseFront(head, right - left);
        }
        ListNode pre, start;
        int i = 2, n = right - left;
        pre = head;
        start = head.next;
        while (i < left) {
            pre = pre.next;
            start = start.next;
            i++;
        }
        pre.next = reverseFront(start, n);
        return head;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode res = s.reverseBetween(head, 2, 4);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
