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
class BSTIterator {
    Deque<TreeNode> visits = new ArrayDeque<>();
    public BSTIterator(TreeNode root) {
        visits.push(root);
        while (root.left != null) {
            visits.push(root.left);
            root = root.left;
        }
    }

    public int next() {
        TreeNode cur = visits.pop();
        int ret = cur.val;
        cur = cur.right;
        if (cur != null) {
            visits.push(cur);
            while (cur.left != null) {
                visits.push(cur.left);
                cur = cur.left;
            }
        }
        return ret;
    }

    public boolean hasNext() {
        return !visits.isEmpty();
    }
}
public class Main {
    public static void main(String[] args) {
        TreeNode r = new TreeNode(7);
        r.left = new TreeNode(3);
        r.right = new TreeNode(15);
        r.right.left = new TreeNode(9);
        r.right.right = new TreeNode(20);
        BSTIterator bSTIterator = new BSTIterator(r);
        System.out.println(bSTIterator.next());    // return 3
        System.out.println(bSTIterator.next());    // return 7
        System.out.println(bSTIterator.hasNext()); // return True
        System.out.println(bSTIterator.next());    // return 9
        System.out.println(bSTIterator.hasNext()); // return True
        System.out.println(bSTIterator.next());    // return 15
        System.out.println(bSTIterator.hasNext()); // return True
        System.out.println(bSTIterator.next());    // return 20
        System.out.println(bSTIterator.hasNext()); // return False
    }
}
//     ____7_____
//    /          \
//    3          15
//               / \
//              9   20
