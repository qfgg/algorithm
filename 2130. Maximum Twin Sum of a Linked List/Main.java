import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    ListNode cur;
    int max;
    void dfs(ListNode halfHead) {
        if (halfHead == null) {
            return;
        }
        dfs(halfHead.next);
        max = Math.max(halfHead.val + cur.val, max);
        cur = cur.next;
    }
    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head, halfHead;
        while (fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        cur = head;
        max = 0;
        dfs(slow.next);
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(5);
        head.next = new ListNode(4);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        int res = s.pairSum(head);
        System.out.println(res);
    }
}
