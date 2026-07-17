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
class Solution {
    public Node connect(Node root) {
        Node nextLevelStart = null, cur = root, pre = null;
        while (cur != null) {
            while (cur != null) {
                if (cur.left == null && cur.right == null) {
                    cur = cur.next;
                    continue;
                }
                if (cur.left != null && cur.right != null) {
                    if (nextLevelStart == null) {
                        nextLevelStart = cur.left;
                    }
                    cur.left.next = cur.right;
                    if (pre != null) {
                        pre.next = cur.left;
                    }
                    pre = cur.right;
                } else if (cur.left != null) {
                    if (nextLevelStart == null) {
                        nextLevelStart = cur.left;
                    }
                    if (pre != null) {
                        pre.next = cur.left;
                    }
                    pre = cur.left;
                } else {
                    if (nextLevelStart == null) {
                        nextLevelStart = cur.right;
                    }
                    if (pre != null) {
                        pre.next = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = nextLevelStart;
            nextLevelStart = null;
            pre = null;
        }
        return root;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        Node root = new Node(1);
        Node res = s.connect(root);
        System.out.println(res);
    }
}
