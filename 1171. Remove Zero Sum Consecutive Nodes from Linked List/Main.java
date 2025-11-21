import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Set<Integer> presumSet = new HashSet<>();
        presumSet.add(0);
        ListNode h, cur = head;
        int presum =  0, tmp, top;
        while (cur != null) {
            if (cur.val != 0) {
                tmp = presum + cur.val;
                if (presumSet.contains(tmp)) {
                    while (presum != tmp) {
                        presumSet.remove(presum);
                        top = stack.pop();
                        presum -= top;
                    }
                } else {
                    presum = tmp;
                    presumSet.add(presum);
                    stack.push(cur.val);
                }
            }
            cur = cur.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        h = new ListNode(stack.removeLast());
        cur = h;
        while (!stack.isEmpty()) {
            cur.next = new ListNode(stack.removeLast());
            cur = cur.next;
        }
        return h;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(-2);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(0);
        head.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next.next = new ListNode(2);
        ListNode res = s.removeZeroSumSublists(head);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
