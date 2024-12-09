import java.util.*;


class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class Main {
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head, n = new Node(cur.val), preN = n, curN;
        map.put(cur, n);
        cur = cur.next;
        while (cur != null) {
            curN = new Node(cur.val);
            preN.next = curN;
            map.put(cur, curN);
            preN = curN;
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                map.get(cur).random = map.get(cur.random);
            }
            cur = cur.next;
        }
        return n;
    }
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n1.next = n2;
        n1.random = n2;
        n2.random = n2;
        Node head = copyRandomList(n1);
        System.out.println(head);
    }
}
