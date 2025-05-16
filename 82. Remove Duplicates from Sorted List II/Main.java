import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre, cur, next;
        while (true) {
            if (head == null || head.next == null) {
                return head;
            }
            next = head.next;
            while (next != null && next.val == head.val) {
                next = next.next;
            }
            if (next == head.next) {
                break;
            }
            head = next;
        }
        pre = head;
        cur = head.next;
        if (cur == null || cur.next == null) {
            return head;
        }
        while (cur.next != null && cur.next.val != cur.val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur.next != null) {
            pre.next = deleteDuplicates(cur);
        }
        return head;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        ListNode res = s.deleteDuplicates(head);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
