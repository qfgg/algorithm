import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head, even = head.next, oddtail = odd, evenhead = even;
        while (odd != null) {
            if (even != null) {
                odd.next = even.next;
            }
            if (even != null && even.next != null) {
                even.next = even.next.next;
            }
            if (odd.next == null) {
                oddtail = odd;
            }
            odd = odd.next;
            if (even != null) {
                even = even.next;
            }
        }
        oddtail.next = evenhead;
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
        ListNode res = s.oddEvenList(head);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
