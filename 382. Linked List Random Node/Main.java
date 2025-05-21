import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    List<Integer> l;
    int size;
    Random rd;
    public Solution(ListNode head) {
        l = new ArrayList<>();
        rd = new Random();
        ListNode cur = head;
        size = 0;
        while (cur != null) {
            l.add(cur.val);
            size++;
            cur = cur.next;
        }
    }

    public int getRandom() {
        return l.get(rd.nextInt(size));
    }
}
public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        Solution solution = new Solution(head);
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
        System.out.println(solution.getRandom());
    }
}
