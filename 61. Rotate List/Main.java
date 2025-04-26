import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public void dfs(ListNode head, int k, int count, int[] pos, ListNode[] res) {
        if (head.next == null) {
            res[0] = head;
            if (k % count != 0) {
                pos[0] = count - (k % count);
            }
            return;
        }
        dfs(head.next, k, count + 1, pos, res);
        if (count == pos[0]) {
            res[1] = head;
        }
    }
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode[] res = new ListNode[2];
        int[] pos = new int[1];
        pos[0] = -1;
        dfs(head, k, 1, pos, res);
        if (pos[0] == -1) {
            return head;
        }
        res[0].next = head;
        head = res[1].next;
        res[1].next = null;
        return head;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode res = s.rotateRight(head, 4);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
