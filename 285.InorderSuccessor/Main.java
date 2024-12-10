import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right == null) {
            TreeNode preRoot = null;
            while (root != p && root != null) {
                if (p.val > root.val) {
                    root = root.right;
                } else {
                    preRoot = root;
                    root = root.left;
                }
            }
            return preRoot;
        }

        TreeNode cur = p.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }
    public static void main(String args[]) {
        TreeNode r = new TreeNode(6);
        TreeNode p = new TreeNode(2);
        r.left = p;
        r.right = new TreeNode(10);
        r.left.left = new TreeNode(1);
        r.left.right = new TreeNode(4);
        r.left.right.left = new TreeNode(3);
        TreeNode q = new TreeNode(5);
        r.left.right.right = q;
        r.right.left = new TreeNode(8);
        r.right.left.left = new TreeNode(7);
        r.right.left.right = new TreeNode(9);
        TreeNode ans = inorderSuccessor(r, p);
        System.out.println(ans == null ? ans : ans.val);
        ans = inorderSuccessor(r, q);
        System.out.println(ans == null ? ans : ans.val);
    }
}

//     ____6_____
//    /          \
//    2          10
//   / \        /
//  1   4      8
//     / \    / \
//    3   5  7   9
