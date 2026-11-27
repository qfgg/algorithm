import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode reverse(ListNode head) {
        ListNode cur = head, n1 = head.next, n2;
        head.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = cur;
            cur = n1;
            n1 = n2;
        }
        return cur;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n1 = 0, n2 = 0, n3 = 0, i;
        ListNode l, s, cur, head, hcur, scur, rv;
        cur = l1;
        while (cur != null) {
            n1++;
            cur = cur.next;
        }
        cur = l2;
        while (cur != null) {
            n2++;
            cur = cur.next;
        }

        if (n1 > n2) {
            l = l1;
            s = l2;
            n3 = n1 - n2;
        } else {
            l = l2;
            s = l1;
            n3 = n2 - n1;
        }
        head = new ListNode(l.val);
        hcur = head;
        cur = l;
        while (n3 > 1) {
            n3--;
            hcur.next = cur.next == null ? null : new ListNode(cur.next.val);
            hcur = hcur.next;
            cur = cur.next;
        }
        scur = s;
        if (n3 == 0) {
            head.val += s.val;
            scur = scur.next;
        }
        while (cur.next != null) {
            hcur.next = new ListNode(cur.next.val + scur.val);
            hcur = hcur.next;
            scur = scur.next;
            cur = cur.next;
        }
        rv = reverse(head);
        cur = rv;
        while (cur != null) {
            if (cur.val >= 10) {
                cur.val -= 10;
                if (cur.next == null) {
                    cur.next = new ListNode(1);
                } else {
                    cur.next.val++;
                }
            }
            cur = cur.next;
        }
        head = reverse(rv);
        return head;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(9);
//        l1.next.next.next = new ListNode(9);
        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(7);
        l2.next.next = new ListNode(4);
        ListNode res = s.addTwoNumbers(l1, l2);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
