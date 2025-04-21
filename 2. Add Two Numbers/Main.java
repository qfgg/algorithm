import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur1 = l1, cur2 = l2, pre = null, cur, sum = null;
        int d1, d2, d, carry = 0;
        while (cur1 != null || cur2 != null) {
            d1 = 0;
            d2 = 0;
            if (cur1 != null) {
                d1 = cur1.val;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                d2 = cur2.val;
                cur2 = cur2.next;
            }
            if (d1 + d2 + carry >= 10) {
                d = d1 + d2 + carry - 10;
                carry = 1;
            } else {
                d = d1 + d2 + carry;
                carry = 0;
            }
            cur = new ListNode(d);
            if (sum == null) {
                sum = cur;
            }
            if (pre != null) {
                pre.next = cur;
            }
            pre = cur;
        }
        if (carry == 1) {
            pre.next = new ListNode(1);
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(9);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(9);
        ListNode sum = s.addTwoNumbers(l1, l2);
        ListNode cur = sum;
        while (cur != null) {
            System.out.print(cur.val);
            System.out.print(' ');
            cur = cur.next;
        }
        System.out.println();
    }
}
