import java.util.*;

class Node {
    int val;
    Node left;
    Node right;
    Node parent;
    Node() {}
    Node(int val) {
        this.val = val;
    }
    Node(int val, Node left, Node right, Node parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}

public class Main {
    public static Node inorderSuccessor(Node p) {
        if (p == null) {
            return null;
        }
        Node is;
        if (p.right != null) {
            is = p.right;
            while (is.left != null) {
                is = is.left;
            }
            return is;
        }
        is = p;
        while (is.parent != null && is.parent.left != is) {
            is = is.parent;
        }
        return is.parent;
    }
    public static void main(String args[]) {
        Node r = new Node(15);
        Node n1 = new Node(6);
        r.left = n1;
        Node n2 = new Node(18);
        r.right = n2;
        Node n3 = new Node(3);
        n1.left = n3;
        Node n4 = new Node(7);
        n1.right = n4;
        Node n5 = new Node(17);
        n2.left = n5;
        Node n6 = new Node(20);
        n2.right = n6;
        Node n7 = new Node(2);
        n3.left = n7;
        Node n8 = new Node(4);
        n3.right = n8;
        Node n9 = new Node(13);
        n4.right = n9;
        Node n10 = new Node(9);
        n9.left = n10;
        n1.parent = r;
        n2.parent = r;
        n3.parent = n1;
        n4.parent = n1;
        n5.parent = n2;
        n6.parent = n2;
        n7.parent = n3;
        n8.parent = n3;
        n9.parent = n4;
        n10.parent = n9;
        Node ans = inorderSuccessor(n1); // 6 -> 7
        System.out.println(ans == null ? ans : ans.val);
        ans = inorderSuccessor(r); // 15 -> 17
        System.out.println(ans == null ? ans : ans.val);
        ans = inorderSuccessor(n9); // 13 -> 15
        System.out.println(ans == null ? ans : ans.val);
        ans = inorderSuccessor(n6); // 20 -> null
        System.out.println(ans == null ? ans : ans.val);
    }
}
//     ______15_____
//    /             \
//    6              18
//   / \            /  \
//  3   7          17  20
// / \    \
// 2  4    13
//         /
//        9
