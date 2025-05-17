import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode lhead = null, lcur = null, rhead = null, rcur = null, cur;
        cur = head;
        while (cur != null) {
            if (cur.val < x) {
                if (lcur == null) {
                    lhead = cur;
                    lcur = cur;
                } else {
                    lcur.next = cur;
                    lcur = cur;
                }
            } else {
                if (rcur == null) {
                    rhead = cur;
                    rcur = cur;
                } else {
                    rcur.next = cur;
                    rcur = cur;
                }
            }
            cur = cur.next;
        }
        if (rhead == null) {
            lcur.next = null;
            return lhead;
        }
        rcur.next = null;
        if (lhead == null) {
            return rhead;
        }
        lcur.next = rhead;
        return lhead;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        ListNode res = s.partition(head, 3);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
