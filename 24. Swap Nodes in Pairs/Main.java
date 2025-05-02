import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode l1 = head, l2 = head.next;
        l1.next = swapPairs(l2.next);
        l2.next = l1;
        return l2;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode res = s.swapPairs(head);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
