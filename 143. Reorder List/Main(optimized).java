import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    ListNode reverse(ListNode head) {
        ListNode cur = head, next = head.next, nextnext;
        head.next = null;
        while (next != null) {
            nextnext = next.next;
            next.next = cur;
            cur = next;
            next = nextnext;
        }
        return cur;
    }
    void merge(ListNode h1, ListNode h2) {
        ListNode cur1 = h1, cur2 = h2, next1, next2;
        while (cur1 != null) {
            next1 = cur1.next;
            next2 = cur2 != null ? cur2.next : null;
            cur1.next = cur2;
            if (cur2 != null) {
                cur2.next = next1;
            }
            cur1 = next1;
            cur2 = next2;
        }
    }
    public void reorderList(ListNode head) {
        if (head.next == null) {
            return;
        }
        ListNode fast = head, mid = head, head2;
        while (fast != null) {
            fast = fast.next;
            if (fast != null && fast.next != null) {
                fast = fast.next;
                mid = mid.next;
            }
        }
        head2 = mid.next;
        mid.next = null;
        head2 = reverse(head2);
        merge(head, head2);
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
        s.reorderList(head);
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
