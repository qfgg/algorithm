import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public void reorderList(ListNode head) {
        Deque<ListNode> dq = new ArrayDeque<>();
        ListNode cur = head, pre = null;
        boolean isFront = true;
        while (cur != null) {
            dq.add(cur);
            cur = cur.next;
        }
        while (!dq.isEmpty()) {
            cur = isFront ? dq.removeFirst() : dq.removeLast();
            if (pre != null) {
                pre.next = cur;
            }
            pre = cur;
            pre.next = null;
            isFront = !isFront;
        }
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
