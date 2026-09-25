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
    TreeNode buildBBST(List<Integer> list, int start, int end) {
        TreeNode ret;
        int mid = (start + end) / 2;
        ret = new TreeNode(list.get(mid));
        ret.left = start == mid ? null : buildBBST(list, start, mid - 1);
        ret.right = mid == end ? null : buildBBST(list, mid + 1, end);
        return ret;
    }
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        return buildBBST(list, 0, list.size() - 1);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        ListNode head = new ListNode(-10);
        TreeNode ret = slt.sortedListToBST(head);
        System.out.println(ret);
    }
}
