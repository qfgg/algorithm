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
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        TreeNode cur, pre;
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        pre = root;
        cur = root.right;
        while (cur.left != null) {
            pre = cur;
            cur = cur.left;
        }
        if (pre.right == cur) {
            pre.right = cur.right;
        } else {
            pre.left = cur.right;
        }
        root.val = cur.val;
        return root;
    }
    public static void main(String args[]) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(8);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(9);
        TreeNode ans = deleteNode(root, 2);
        System.out.println(ans);
    }
}
//     ____6_____
//    /          \
//    2          10
//   / \        /
//  1   4      8
//     / \    / \
//    3   5  7   9
