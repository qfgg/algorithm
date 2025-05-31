import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    ListNode mergeSort(ListNode head, int size) {
        int i;
        ListNode l1, l2, tail1, tail2, dummy = new ListNode(), pre = dummy, cur = head, mergehead, mergecur = null, mergetail = null, next;
        dummy.next = head;
        while (cur != null) {
            l1 = cur;
            tail1 = l1;
            for (i = 1; i < size && tail1.next != null; i++) {
                tail1 = tail1.next;
            }
            l2 = tail1.next;
            tail2 = l2;
            for (i = 1; i < size && tail2 != null && tail2.next != null; i++) {
                tail2 = tail2.next;
            }
            next = null;
            tail1.next = null;
            if (tail2 != null) {
                next = tail2.next;
                tail2.next = null;
            }
            mergehead = null;
            while (l1 != null || l2 != null) {
                if (l1 == null || (l2 != null && l1.val > l2.val)) {
                    if (mergehead == null) {
                        mergehead = l2;
                        mergecur = l2;
                    } else {
                        mergecur.next = l2;
                        mergecur = l2;
                    }
                    if (l1 == null && l2.next == null) {
                        mergetail = l2;
                    }
                    l2 = l2.next;
                } else if (l2 == null || (l1 != null && l1.val <= l2.val)) {
                    if (mergehead == null) {
                        mergehead = l1;
                        mergecur = l1;
                    } else {
                        mergecur.next = l1;
                        mergecur = l1;
                    }
                    if (l1.next == null && l2 == null) {
                        mergetail = l1;
                    }
                    l1 = l1.next;
                }
            }
            pre.next = mergehead;
            pre = mergetail;
            mergetail.next = next;
            cur = next;
        }
        return dummy.next;
    }
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 0, size = 1;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        while (size < len) {
            head = mergeSort(head, size);
            size = size * 2;
        }
        return head;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        ListNode res = s.sortList(head);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
