import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Deque<ListNode> s = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            s.push(cur);
            cur = cur.next;
        }
        while (n > 0) {
            cur = s.pop();
            n--;
        }
        if (s.isEmpty()) {
            head = cur.next;
        } else {
            s.peek().next = cur.next;
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
