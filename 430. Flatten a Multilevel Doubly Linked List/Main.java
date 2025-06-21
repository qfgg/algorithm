import java.util.*;


class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}
class Solution {
    Node dfs(Node head) {
        Node cur = head, tail = null, childTail;
        while (cur != null) {
            if (cur.child != null) {
                childTail = dfs(cur.child);
                childTail.next = cur.next;
                if (cur.next != null) {
                    cur.next.prev = childTail;
                }
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
                if (childTail != null && childTail.next == null) {
                    tail = childTail;
                }
                cur = childTail.next;
            } else {
                if (cur != null && cur.next == null) {
                    tail = cur;
                }
                cur = cur.next;
            }
        }
        return tail;
    }
    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        dfs(head);
        return head;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        Node head = new Node();
        head.val = 1;
        head.next = new Node();
        head.next.prev = head;
        head.next.val = 2;
        head.child = new Node();
        head.child.val = 3;
        s.flatten(head);
        System.out.println();
    }
}
