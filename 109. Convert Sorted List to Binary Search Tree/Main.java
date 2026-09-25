import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    TreeNode build(ListNode head, int start, int end, ListNode[] right) {
        if (start == end) {
            right[0] = head;
            return new TreeNode(head.val);
        }
        int mid = (start + end) / 2;
        TreeNode h;
        if (start == mid) {
            h = new TreeNode(head.val);
            h.right = new TreeNode(head.next.val);
            right[0] = head.next;
            return h;
        }
        TreeNode lroot = build(head, start, mid - 1, right);
        h = new TreeNode(right[0].next.val);
        h.left = lroot;
        h.right = build(right[0].next.next, mid + 1, end, right);
        return h;
    }
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        ListNode[] right = new ListNode[1];
        return build(head, 0, n - 1, right);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);
        TreeNode ret = slt.sortedListToBST(head);
        System.out.println(ret);
    }
}