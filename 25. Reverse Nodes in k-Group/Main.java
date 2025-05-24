import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    private ListNode reverseFront(ListNode head, ListNode tail, int k) {
        if (tail == null) {
            return head;
        }
        int i = 1;
        ListNode finalHead = tail, start = head, end = head, cur = end.next, next;
        while (i < k) {
            next = cur.next;
            cur.next = start;
            end.next = next;
            start = cur;
            cur = next;
            i++;
        }
        return finalHead;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head.next == null || k == 1) {
            return head;
        }
        ListNode tail, finalHead, preTail, nextHead;
        int i;
        i = 1;
        tail = head;
        while (i < k && tail != null) {
            tail = tail.next;
            i++;
        }
        if (tail == null) {
            return head;
        }
        nextHead = tail.next;
        preTail = head;
        finalHead = reverseFront(head, tail, k);
        while (nextHead != null) {
            i = 1;
            head = nextHead;
            tail = head;
            while (i < k && tail != null) {
                tail = tail.next;
                i++;
            }
            if (tail == null) {
                preTail.next = head;
                break;
            }
            nextHead = tail.next;
            preTail.next = reverseFront(head, tail, k);
            preTail = head;
        }
        return finalHead;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode res = s.reverseKGroup(head, 4);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
