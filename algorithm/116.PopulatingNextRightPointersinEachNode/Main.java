import java.util.*;


class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class Main {
    public static Node connect(Node root) {
        Node nextStart = root, cur;
        while (nextStart != null) {
            cur = nextStart;
            while (cur != null) {
                if (cur.left != null && cur.right != null) {
                    cur.left.next = cur.right;
                }
                if (cur.next != null && cur.right != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            nextStart = nextStart.left;
        }
        return root;
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        Node ans = connect(root);
    }
}
