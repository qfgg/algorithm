import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    private int remove(ListNode head, int n) {
        int num;
        if (head.next == null) {
            num = 1;
        } else {
            num = remove(head.next, n) + 1;
        }
        if (num == n + 1) {
            head.next = head.next.next;
        }
        return num;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int num = remove(head, n);
        if (num == n) {
            return head.next;
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
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        s.removeNthFromEnd(head, 2);
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            System.out.print(' ');
            cur = cur.next;
        }
        System.out.println();
    }
}
