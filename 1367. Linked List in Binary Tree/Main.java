import java.util.*;

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
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    boolean isStartingPath(ListNode head, TreeNode root) {
        if (head != null && root == null) {
            return false;
        }
        if (head.val == root.val) {
            if (head.next == null) {
                return true;
            }
            return isStartingPath(head.next, root.left) || isStartingPath(head.next, root.right);
        }
        return false;
    }
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head != null && root == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        if (head.val == root.val && isStartingPath(head, root)) {
            return true;
        }
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(1);
        root.right.left.left = new TreeNode(9);
        ListNode head = new ListNode(1);
        head.next = new ListNode(10);
        boolean res = s.isSubPath(head, root);
        System.out.println(res);
    }
}
