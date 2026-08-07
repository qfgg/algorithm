import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
class Solution {
    public void deleteNode(ListNode node) {
        ListNode pre = node, cur = node;
        while (cur.next != null) {
            cur.val = cur.next.val;
            pre = cur;
            cur = cur.next;
        }
        pre.next = null;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode node = new ListNode(5);
        s.deleteNode(node);
    }
}
