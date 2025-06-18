import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        ListNode cur = head, dummy = new ListNode(), pre;
        int len = 0, base, extra, i = 0, j;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        base = len / k;
        extra = len % k;
        cur = head;
        dummy.next = cur;
        pre = dummy;
        while (cur != null) {
            pre.next = null;
            if (base > 0) {
                res[i] = cur;
                j = 1;
                while (j < base) {
                    j++;
                    cur = cur.next;
                }
                if (extra > 0) {
                    cur = cur.next;
                    extra--;
                }
            } else {
                if (extra > 0) {
                    res[i] = cur;
                    extra--;
                }
            }
            i++;
            pre = cur;
            if (cur != null) {
                cur = cur.next;
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode[] res = s.splitListToParts(head, 5);
        for (ListNode cur: res) {
            if (cur != null) {
                System.out.print(cur.val);
                System.out.print(' ');
                cur = cur.next;
                while (cur != null) {
                    System.out.print(cur.val);
                    System.out.print(' ');
                    cur = cur.next;
                }
            } else {
                System.out.print("null");
            }
            System.out.println();
        }
    }
}
